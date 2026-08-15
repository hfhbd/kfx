package io.github.hfhbd.kfx.kotlin.ktor.client

import app.softwork.serviceloader.ServiceLoader
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.NameAllocator
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.UNIT
import com.squareup.kotlinpoet.joinToCode
import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.kotlin.KotlinPoetCodeGenerator
import io.github.hfhbd.kfx.kotlin.ktor.supportedBySerialization
import io.github.hfhbd.kfx.kotlin.ktor.toHttpCode
import io.github.hfhbd.kfx.kotlin.ktor.toKtor
import io.github.hfhbd.kfx.kotlin.ktor.toKtorPoetType
import io.github.hfhbd.kfx.kotlin.toCodeBlock
import io.github.hfhbd.kfx.kotlin.toKdoc
import io.github.hfhbd.kfx.kotlin.toPoetType
import java.nio.file.Path

@ServiceLoader(CodeGenerator::class)
class KtorClientGenerator : KotlinPoetCodeGenerator {
    override fun generate(codeGenTree: CodeGenTree, outputDirectory: Path) {
        val files = generateFileSpec(codeGenTree)
        for (file in files) {
            file.writeTo(outputDirectory)
        }
    }

    override fun generateFileSpec(codeGenTree: CodeGenTree): List<FileSpec> {
        val operations = codeGenTree.operations.map { it.generateFileSpec() }
        val auth = codeGenTree.auth.map {
            it.generateFileSpec()
        }
        return operations + auth
    }

    private fun CodeGenTree.Auth.generateFileSpec(): FileSpec = when (this) {
        is CodeGenTree.Auth.OAuth2 -> {
            FileSpec.builder(
                packageName = if (operation.packageName.isEmpty()) {
                    "auth"
                } else {
                    "${operation.packageName}.auth"
                },
                fileName = operation.name + "Auth",
            ).apply {
                addFunction(addOAuth2())
            }.build()
        }

        is CodeGenTree.Auth.Http -> {
            FileSpec.builder(
                packageName = if (packageName.isEmpty()) {
                    "auth"
                } else {
                    "$packageName.auth"
                },
                fileName = name + "Auth",
            ).apply {
                addFunction(addHttp())
            }.build()
        }
    }

    private fun CodeGenTree.Auth.OAuth2.addOAuth2(): FunSpec = FunSpec.builder(operation.name + "Auth").apply {
        val nameAllocator = NameAllocator()
        val t = TypeVariableName("T", ClassName("io.ktor.client.engine", "HttpClientEngineConfig"))
        addTypeVariable(t)
        receiver(ClassName("io.ktor.client", "HttpClientConfig").parameterizedBy(t))
        when (grantType) {
            CodeGenTree.Auth.OAuth2.GrantType.ClientCredentials -> {
                addParameter("clientId", STRING)
                addParameter("clientSecret", STRING)
            }
        }

        addParameter(ParameterSpec.builder("builder", httpRequestBuilderLambda).defaultValue("{}").build())

        val documentation = operation.documentation
        if (!documentation.isNullOrBlank()) {
            addKdoc(documentation.toKdoc())
        }

        beginControlFlow("%M", MemberName("io.ktor.client.plugins.auth", "Auth", isExtension = true))
        beginControlFlow("%M", MemberName("io.ktor.client.plugins.auth.providers", "bearer", isExtension = true))
        beginControlFlow("refreshTokens")

        getResponse(operation, nameAllocator, "builder", withReceiver = CodeBlock.of("client")) {
            when (grantType) {
                CodeGenTree.Auth.OAuth2.GrantType.ClientCredentials -> {
                    addStatement("%M(%S, %S)", parameter, "grant_type", "client_credentials")
                    addStatement(
                        "%M(clientId, clientSecret)",
                        MemberName("io.ktor.client.request", "basicAuth", isExtension = true),
                    )
                }
            }
            addStatement("%M(%L)", contentType, ContentType.FormUrlEncoded.toKtor())
            addStatement("markAsRefreshTokenRequest()")
        }
        addStatement("val output = %L", getOutput(operation.output!!.toKtorPoetType(false)))
        addStatement(
            """%T(output.accessToken, output.refreshToken ?: "")""",
            ClassName("io.ktor.client.plugins.auth.providers", "BearerTokens"),
        )
        endControlFlow()
        endControlFlow()
        endControlFlow()
    }.build()

    private fun CodeGenTree.Auth.Http.addHttp(): FunSpec = FunSpec.builder(name + "Auth").apply {
        val t = TypeVariableName("T", ClassName("io.ktor.client.engine", "HttpClientEngineConfig"))
        addTypeVariable(t)
        receiver(ClassName("io.ktor.client", "HttpClientConfig").parameterizedBy(t))
        val schema = schema
        when (schema) {
            CodeGenTree.Auth.Http.Schema.Basic -> {
                addParameter("userName", STRING)
                addParameter("password", STRING)
            }

            CodeGenTree.Auth.Http.Schema.Bearer -> {
                addParameter("token", STRING)
            }

            is CodeGenTree.Auth.Http.Schema.Header -> {
                addParameter("apiKey", STRING)
            }
        }

        beginControlFlow("%M", MemberName("io.ktor.client.plugins", "defaultRequest", isExtension = true))
        when (schema) {
            CodeGenTree.Auth.Http.Schema.Basic -> {
                addStatement(
                    "%M(userName, password)",
                    MemberName("io.ktor.client.request", "basicAuth", isExtension = true),
                )
            }

            CodeGenTree.Auth.Http.Schema.Bearer -> {
                addStatement("%M(token)", MemberName("io.ktor.client.request", "bearerAuth", isExtension = true))
            }

            is CodeGenTree.Auth.Http.Schema.Header -> {
                addStatement(
                    "%M(%S, apiKey)",
                    MemberName("io.ktor.client.request", "header", isExtension = true),
                    schema.headerName,
                )
            }
        }
        endControlFlow()
    }.build()

    private fun CodeGenTree.Operation.generateFileSpec(): FileSpec = FileSpec.builder(
        packageName = if (packageName.isEmpty()) "client" else "$packageName.client",
        fileName = name,
    ).apply {
        addFunction(generateFunSpec())
    }.build()

    private fun FunSpec.Builder.getResponse(
        operation: CodeGenTree.Operation,
        nameAllocator: NameAllocator,
        builderName: String,
        withReceiver: CodeBlock? = null,
        custom: FunSpec.Builder.() -> Unit = {},
    ) {
        beginControlFlow(
            "val response = %L%M%L",
            if (withReceiver != null) {
                CodeBlock.of("%L.", withReceiver)
            } else {
                CodeBlock.of("")
            },
            operation.method.toPoet(),
            if (operation.path != null) {
                CodeBlock.of("(urlString = %P)", operation.path!!.removePrefix("/"))
            } else {
                CodeBlock.of("")
            },
        )

        if (operation.headers.isNotEmpty()) {
            for (header in operation.headers) {
                addStatement(
                    "%M(%S, %L)",
                    MemberName("io.ktor.client.request", "header", isExtension = true),
                    header.name,
                    CodeGenTree.Expression.Parameter(header).toCodeBlock(nameAllocator),
                )
            }
        }
        val soapAction = operation.soapAction
        if (soapAction != null) {
            addStatement(
                "%M(%S, %S)",
                MemberName("io.ktor.client.request", "header", isExtension = true),
                "SOAPAction",
                soapAction,
            )
        }

        if (operation.queryParameters.isNotEmpty()) {
            for (queryParameter in operation.queryParameters) {
                addStatement(
                    "%M(%S, %L)",
                    parameter,
                    queryParameter.name,
                    CodeGenTree.Expression.Parameter(queryParameter).toCodeBlock(nameAllocator),
                )
            }
        }

        if (operation.inputContentType != null) {
            addStatement(
                "%M(%L)",
                contentType,
                operation.inputContentType!!.toKtor(),
            )

            if (operation.input != null && operation.inputContentType!!.supportedBySerialization()) {
                val inputWrapper = operation.inputWrapper
                addStatement(
                    "%M(%L%L%L)",
                    MemberName("io.ktor.client.request", "setBody", isExtension = true),
                    if (inputWrapper != null) CodeBlock.of("\n") else CodeBlock.of(""),
                    if (inputWrapper != null) {
                        CodeBlock.builder().add("%L", inputWrapper.toCodeBlock(nameAllocator)).build()
                    } else {
                        CodeBlock.of("input")
                    },
                    if (inputWrapper != null) CodeBlock.of(",\n") else CodeBlock.of(""),
                )
            }
        }

        custom()
        addStatement("%L()", builderName)
        endControlFlow()
    }

    private val parameter = MemberName("io.ktor.client.request", "parameter", isExtension = true)
    private val contentType = MemberName("io.ktor.http", "contentType", isExtension = true)
    private val httpRequestBuilderLambda = LambdaTypeName.get(
        receiver = ClassName("io.ktor.client.request", "HttpRequestBuilder"),
        returnType = UNIT,
    ).copy(suspending = true)

    private fun CodeGenTree.Operation.HttpMethod.toPoet(): MemberName = MemberName(
        "io.ktor.client.request",
        when (this) {
            CodeGenTree.Operation.HttpMethod.Head -> "head"
            CodeGenTree.Operation.HttpMethod.Get -> "get"
            CodeGenTree.Operation.HttpMethod.Post -> "post"
            CodeGenTree.Operation.HttpMethod.Put -> "put"
            CodeGenTree.Operation.HttpMethod.Patch -> "patch"
            CodeGenTree.Operation.HttpMethod.Delete -> "delete"
            CodeGenTree.Operation.HttpMethod.Options -> "options"
            CodeGenTree.Operation.HttpMethod.Trace -> "trace"
            CodeGenTree.Operation.HttpMethod.Query -> "query"
        },
        isExtension = true,
    )

    private fun getOutput(type: TypeName): CodeBlock = CodeBlock.of(
        "response.%M<%T>()",
        MemberName("io.ktor.client.call", "body", isExtension = true),
        type,
    )

    private fun CodeGenTree.Operation.generateFunSpec(): FunSpec {
        val function = FunSpec.builder(name)
        val nameAllocator = NameAllocator()
        val documentation = documentation
        if (!documentation.isNullOrBlank()) {
            function.addKdoc(documentation.toKdoc())
        }
        val fault = fault
        if (fault != null) {
            function.addAnnotation(
                AnnotationSpec.builder(ClassName("kotlin", "Throws"))
                    .addMember("%T::class", fault.toKtorPoetType(read = true)).build(),
            )
        }
        if (deprecated) {
            function.addAnnotation(
                AnnotationSpec.builder(ClassName("kotlin", "Deprecated"))
                    .addMember("message = %S", "")
                    .build(),
            )
        }
        function.addModifiers(KModifier.SUSPEND)
        function.receiver(ClassName("io.ktor.client", "HttpClient"))

        val input = input
        if (input != null && inputContentType?.supportedBySerialization() == true) {
            function.addParameter("input", input.toKtorPoetType(read = true))
        }
        for (parameter in parameters) {
            function.addParameter(parameter.toPoet(nameAllocator))
        }
        for (parameter in headers) {
            function.addParameter(parameter.toPoet(nameAllocator))
        }

        for (queryParameter in queryParameters) {
            function.addParameter(queryParameter.toPoet(nameAllocator))
        }

        val builderName = nameAllocator.newName("builder")
        function.addParameter(ParameterSpec.builder(builderName, httpRequestBuilderLambda).defaultValue("{}").build())

        val output = output
        val returnType = returnType
        if (returnType != null) {
            function.returns(returnType.toKtorPoetType(read = false).copy(nullable = notFound))
        }

        function.getResponse(this, nameAllocator, builderName)

        val responseBranches = responseBranches
        if (responseBranches != null) {
            function.beginControlFlow("when")
            val success = responseBranches.success
            if (success != null) {
                val successStatusCode = success.statusCode
                if (successStatusCode != null) {
                    function.beginControlFlow(
                        "response.status == %M ->",
                        successStatusCode.toHttpCode(),
                    )
                } else {
                    function.beginControlFlow(
                        "response.status.%M() ->",
                        MemberName("io.ktor.http", "isSuccess", isExtension = true),
                    )
                }
                if (success.response != null) {
                    function.addStatement(
                        "val output = %L",
                        getOutput(
                            (outputWrapperType ?: output!!).toKtorPoetType(read = false),
                        ),
                    )
                    val parameters = (
                        listOf(
                            CodeBlock.of("body = output"),
                        ) + headersToCode(success.headers)
                        ).joinToCode()
                    function.addStatement("return %T(%L)", success.isCondition.toPoetType(), parameters)
                } else if (success.headers.isNotEmpty()) {
                    val parameters = headersToCode(success.headers).joinToCode()
                    function.addStatement("return %T(%L)", success.isCondition.toPoetType(), parameters)
                } else {
                    function.addStatement("return %T", success.isCondition.toPoetType())
                }

                function.endControlFlow()
            }
            val notFound = responseBranches.notFound
            if (notFound != null) {
                function.beginControlFlow(
                    "response.status == %M ->",
                    notFound.statusCode!!.toHttpCode(),
                )
                function.addStatement("return %T", notFound.isCondition.toPoetType())
                function.endControlFlow()
            }

            function.beginControlFlow("else ->")
            val faultOutput = responseBranches.fault.response
            if (faultOutput != null) {
                function.addStatement(
                    "val output = %L",
                    getOutput(
                        (
                            faultWrapper
                                ?: responseBranches.fault.isCondition.members.first().type
                            ).toKtorPoetType(read = false),
                    ),
                )
                val parameters = (
                    listOf(
                        CodeBlock.of("body = output"),
                    ) + headersToCode(responseBranches.fault.headers)
                    ).joinToCode()
                function.addStatement("return %T(%L)", responseBranches.fault.isCondition.toPoetType(), parameters)
            } else if (responseBranches.fault.headers.isNotEmpty()) {
                val parameters = headersToCode(responseBranches.fault.headers).joinToCode()
                function.addStatement("return %T(%L)", responseBranches.fault.isCondition.toPoetType(), parameters)
            } else {
                function.addStatement("return %T", responseBranches.fault.isCondition.toPoetType())
            }

            function.endControlFlow()
            function.endControlFlow()
        } else {
            val nullableOutput = notFound
            if (output != null && nullableOutput) {
                function.beginControlFlow(
                    "if (response.status == %M)",
                    ClassName("io.ktor.http", "HttpStatusCode")
                        .nestedClass("Companion")
                        .member("NotFound"),
                )
                function.addStatement("return null")
                function.endControlFlow()
            }

            if (fault != null) {
                function.beginControlFlow("when")
                function.nextControlFlow(
                    "response.status.%M() ->",
                    MemberName("io.ktor.http", "isSuccess", isExtension = true),
                )
                if (output != null) {
                    function.addStatement(
                        "val output = %L",
                        getOutput(
                            (outputWrapperType ?: output).toKtorPoetType(read = false),
                        ),
                    )
                    function.addStatement(
                        "return %L",
                        outputMember?.toCodeBlock(nameAllocator) ?: CodeBlock.of("output"),
                    )
                }

                function.nextControlFlow("else ->")
                function.addStatement(
                    "val output = %L",
                    getOutput((faultWrapper ?: fault).toKtorPoetType(read = false)),
                )
                function.addStatement(
                    "throw %L",
                    outputMember?.toCodeBlock(nameAllocator) ?: CodeBlock.of("output"),
                )
                function.endControlFlow()
            } else if (output != null) {
                function.addStatement(
                    "val output = %L",
                    getOutput((outputWrapperType ?: output).toKtorPoetType(read = false)),
                )
                function.addStatement(
                    "return %L",
                    outputMember?.toCodeBlock(nameAllocator) ?: CodeBlock.of("output"),
                )
            }
        }

        return function.build()
    }

    private fun headersToCode(headers: Map<String, Pair<String, Boolean>>): List<CodeBlock> =
        headers.map { (headerName, member) ->
            val (memberName, nullable) = member
            CodeBlock.of(
                "$memberName = response.headers[%S]%L",
                headerName,
                if (nullable) "" else "!!",
            )
        }

    private fun CodeGenTree.Operation.Parameter.toPoet(nameAllocator: NameAllocator): ParameterSpec {
        val name = nameAllocator.newName(name, name)
        return ParameterSpec.builder(name, type.toKtorPoetType(read = true).copy(nullable = nullable)).apply {
            val documentation = documentation
            if (!documentation.isNullOrBlank()) {
                addKdoc(documentation.toKdoc())
            }
            val defaultValue = defaultValue
            if (defaultValue != null) {
                defaultValue(
                    defaultValue.toCodeBlock(nameAllocator),
                )
            }
        }.build()
    }
}
