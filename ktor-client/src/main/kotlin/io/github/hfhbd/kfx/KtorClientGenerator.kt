package io.github.hfhbd.kfx

import app.softwork.serviceloader.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator
import java.nio.file.*

@ServiceLoader(CodeGenerator::class)
class KtorClientGenerator : KotlinPoetCodeGenerator {
    override fun generate(codeGenTree: CodeGenTree, outputFolder: Path) {
        val files = generateFileSpec(codeGenTree)
        for (file in files) {
            file.writeTo(outputFolder)
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
        if (documentation != null && documentation.isNotBlank()) {
            addKdoc(documentation.toKdoc())
        }

        beginControlFlow("%M", MemberName("io.ktor.client.plugins.auth", "Auth", isExtension = true))
        beginControlFlow("%M", MemberName("io.ktor.client.plugins.auth.providers", "bearer", isExtension = true))
        beginControlFlow("refreshTokens")

        addCode(
            operation.getResponse(nameAllocator, "builder", withReceiver = CodeBlock.of("client")) {
                CodeBlock.builder().apply {
                    when (grantType) {
                        CodeGenTree.Auth.OAuth2.GrantType.ClientCredentials -> {
                            add("%M(%S, %S)\n", parameter, "grant_type", "client_credentials")
                            add(
                                "%M(clientId, clientSecret)\n",
                                MemberName("io.ktor.client.request", "basicAuth", isExtension = true),
                            )
                        }
                    }
                    add("%M(%L)\n", contentType, ContentType.FormUrlEncoded.toKtor())

                    add("markAsRefreshTokenRequest()")
                }.build()
            },
        )
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
        when (schema) {
            CodeGenTree.Auth.Http.Schema.Basic -> {
                addParameter("userName", STRING)
                addParameter("password", STRING)
            }

            CodeGenTree.Auth.Http.Schema.Bearer -> {
                addParameter("token", STRING)
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
        }
        endControlFlow()
    }.build()

    private fun CodeGenTree.Operation.generateFileSpec(): FileSpec = FileSpec.builder(
        packageName = if (packageName.isEmpty()) "client" else "$packageName.client",
        fileName = name,
    ).apply {
        addFunction(generateFunSpec())
    }.build()

    private fun CodeGenTree.Operation.getResponse(
        nameAllocator: NameAllocator,
        builderName: String,
        withReceiver: CodeBlock? = null,
        custom: (() -> CodeBlock)? = null,
    ): CodeBlock = CodeBlock.builder().apply {
        add("val response = ")
        if (withReceiver != null) {
            add("%L.", withReceiver)
        }
        beginControlFlow(
            "%M%L",
            method.toPoet(),
            if (path != null) {
                CodeBlock.of("(urlString = %P)", path!!.removePrefix("/"))
            } else {
                CodeBlock.of("")
            },
        )

        if (headers.isNotEmpty()) {
            for (header in headers) {
                addStatement(
                    "%M(%S, %L)",
                    MemberName("io.ktor.client.request", "header", isExtension = true),
                    header.name,
                    CodeGenTree.Expression.Parameter(header).toCodeBlock(nameAllocator) {
                        toKtorPoetType(read = true)
                    },
                )
            }
        }

        if (queryParameters.isNotEmpty()) {
            for (queryParameter in queryParameters) {
                addStatement(
                    "%M(%S, %L)",
                    parameter,
                    queryParameter.name,
                    CodeGenTree.Expression.Parameter(queryParameter).toCodeBlock(nameAllocator) {
                        toKtorPoetType(read = true)
                    },
                )
            }
        }

        if (inputContentType != null) {
            addStatement(
                "%M(%L)",
                contentType,
                inputContentType!!.toKtor(),
            )

            if (input != null && inputContentType!!.supportedBySerialization()) {
                val inputWrapper = inputWrapper
                addStatement(
                    "%M(%Lbody = %L)",
                    MemberName("io.ktor.client.request", "setBody", isExtension = true),
                    if (inputWrapper != null) CodeBlock.of("\n") else CodeBlock.of(""),
                    if (inputWrapper != null) {
                        CodeBlock.builder().add(
                            "%L,\n",
                            inputWrapper.toCodeBlock(nameAllocator) {
                                toKtorPoetType(read = true)
                            },
                        ).build()
                    } else {
                        CodeBlock.of("input")
                    },
                )
            }
        }

        if (custom != null) {
            add("%L\n", custom())
        }
        add("%L()\n", builderName)
    }.endControlFlow().build()

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
        },
        isExtension = true,
    )

    private fun getOutput(type: TypeName): CodeBlock = CodeBlock.builder().apply {
        add(
            "response.%M<%T>()",
            MemberName("io.ktor.client.call", "body", isExtension = true),
            type,
        )
    }.build()

    private fun CodeGenTree.Operation.generateFunSpec(): FunSpec {
        val function = FunSpec.builder(name)
        val nameAllocator = NameAllocator()
        val documentation = documentation
        if (documentation != null && documentation.isNotBlank()) {
            function.addKdoc(documentation.toKdoc())
        }
        val fault = fault
        if (fault != null) {
            function.addAnnotation(
                AnnotationSpec.builder(ClassName("kotlin", "Throws"))
                    .addMember("%T::class", fault.toKtorPoetType(read = true)).build(),
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
        if (output != null) {
            function.returns(output.toKtorPoetType(read = false).copy(nullable = nullableOutput != null))
        }

        function.addCode(getResponse(nameAllocator, builderName))

        val nullableOutput = nullableOutput
        if (output != null && nullableOutput != null) {
            function.beginControlFlow(
                "if (response.status.value == %L)",
                nullableOutput,
            )
            function.addStatement("return null")
            function.endControlFlow()
        }

        if (fault != null) {
            function.beginControlFlow(
                "if (response.status.%M())",
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
                    outputMember?.toCodeBlock(nameAllocator) {
                        toKtorPoetType(read = false)
                    } ?: CodeBlock.of("output"),
                )
            }

            function.nextControlFlow("else")
            function.addStatement(
                "val output = %L",
                getOutput((faultWrapper ?: fault).toKtorPoetType(read = false)),
            )
            function.addStatement(
                "throw %L",
                outputMember?.toCodeBlock(nameAllocator) {
                    toKtorPoetType(read = false)
                } ?: CodeBlock.of("output"),
            )
            function.endControlFlow()
        } else if (output != null) {
            function.addStatement(
                "val output = %L",
                getOutput((outputWrapperType ?: output).toKtorPoetType(read = false)),
            )
            function.addStatement(
                "return %L",
                outputMember?.toCodeBlock(nameAllocator) {
                    toKtorPoetType(read = false)
                } ?: CodeBlock.of("output"),
            )
        }

        return function.build()
    }

    private fun CodeGenTree.Operation.Parameter.toPoet(nameAllocator: NameAllocator): ParameterSpec {
        val name = nameAllocator.newName(name, name)
        return ParameterSpec.builder(name, type.toKtorPoetType(read = true).copy(nullable = nullable)).apply {
            val documentation = documentation
            if (documentation != null && documentation.isNotBlank()) {
                addKdoc(documentation.toKdoc())
            }
            val defaultValue = defaultValue
            if (defaultValue != null) {
                defaultValue(
                    defaultValue.toCodeBlock(nameAllocator) {
                        toKtorPoetType(read = true)
                    },
                )
            }
        }.build()
    }
}
