package io.github.hfhbd.kfx.kotlin.spring.server

import app.softwork.serviceloader.ServiceLoader
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.UNIT
import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.StatusCode
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenTree.Operation.HttpMethod
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.kotlin.KotlinPoetCodeGenerator
import io.github.hfhbd.kfx.kotlin.toKdoc
import io.github.hfhbd.kfx.kotlin.toPoetType
import java.nio.file.Path

@ServiceLoader(CodeGenerator::class)
class SpringServerGenerator : KotlinPoetCodeGenerator {
    override fun generate(codeGenTree: CodeGenTree, outputDirectory: Path) {
        val files = generateFileSpec(codeGenTree)
        for (file in files) {
            file.writeTo(outputDirectory)
        }
    }

    // ignore auth
    override fun generateFileSpec(codeGenTree: CodeGenTree): List<FileSpec> = codeGenTree.operations.map {
        it.generateFileSpec()
    }

    private fun CodeGenTree.Operation.generateFileSpec(): FileSpec = FileSpec.builder(
        packageName = if (packageName.isEmpty()) {
            "server"
        } else {
            "$packageName.server"
        },
        fileName = name.replaceFirstChar { it.uppercaseChar() },
    ).apply {
        addFunction(generateInterfaceSpec())
    }.build()

    private fun StatusCode.toHttpCode(): CodeBlock = when (this) {
        StatusCode.OK -> CodeBlock.of("ok()")
        StatusCode.Created -> CodeBlock.of("created(location)")
        StatusCode.Accepted -> CodeBlock.of("accepted()")
        StatusCode.NoContent -> CodeBlock.of("noContent()")
        StatusCode.ResetContent -> CodeBlock.of("status(205)")
        StatusCode.BadRequest -> CodeBlock.of("badRequest()")
        StatusCode.NotFound -> CodeBlock.of("notFound()")
        StatusCode.Unauthorized -> CodeBlock.of("status(401)")
        StatusCode.Forbidden -> CodeBlock.of("status(403)")
        StatusCode.NotAcceptable -> CodeBlock.of("status(406)")
        StatusCode.Conflict -> CodeBlock.of("status(409)")
        StatusCode.LengthRequired -> CodeBlock.of("status(411)")
        StatusCode.ContentTooLarge -> CodeBlock.of("status(413)")
        StatusCode.UnprocessableEntity -> CodeBlock.of("unprocessableEntity()")
        StatusCode.TooManyRequests -> CodeBlock.of("status(429)")
        StatusCode.InternalServerError -> CodeBlock.of("status(500)")
    }

    private fun ContentType.toMediaType(): CodeBlock = when (this) {
        ContentType.ApplicationJson -> CodeBlock.of(
            "%M",
            ClassName("org.springframework.http", "MediaType").member("APPLICATION_JSON"),
        )

        ContentType.ApplicationProblemJson -> CodeBlock.of(
            "%M",
            ClassName("org.springframework.http", "MediaType").member("APPLICATION_PROBLEM_JSON"),
        )

        ContentType.ApplicationProblemXml -> CodeBlock.of(
            "%M",
            ClassName("org.springframework.http", "MediaType").member("APPLICATION_PROBLEM_XML"),
        )

        ContentType.ApplicationSoapXml -> CodeBlock.of(
            "%T(%S, %S)",
            ClassName("org.springframework.http", "MediaType"),
            "application",
            "soap+xml",
        )

        ContentType.ApplicationXml -> CodeBlock.of(
            "%M",
            ClassName("org.springframework.http", "MediaType").member("APPLICATION_XML"),
        )

        ContentType.ApplicationZip -> CodeBlock.of(
            "%T(%S, %S)",
            ClassName("org.springframework.http", "MediaType"),
            "application",
            "zip",
        )

        is ContentType.Custom -> CodeBlock.of(
            "%M(%S)",
            ClassName("org.springframework.http", "MediaType").member("parseMediaType"),
            contentType,
        )

        ContentType.FormUrlEncoded -> CodeBlock.of(
            "%T(%S, %S)",
            ClassName("org.springframework.http", "MediaType"),
            "application",
            "x-www-form-urlencoded",
        )

        ContentType.MultipartFormData -> CodeBlock.of(
            "%M",
            ClassName("org.springframework.http", "MediaType").member("MULTIPART_FORM_DATA"),
        )

        ContentType.OctetStream -> CodeBlock.of(
            "%M",
            ClassName("org.springframework.http", "MediaType").member("APPLICATION_OCTET_STREAM"),
        )

        ContentType.TextCsv -> CodeBlock.of(
            "%T(%S, %S)",
            ClassName("org.springframework.http", "MediaType"),
            "text",
            "csv",
        )

        ContentType.TextPlain -> CodeBlock.of(
            "%M",
            ClassName("org.springframework.http", "MediaType").member("TEXT_PLAIN"),
        )

        ContentType.TextXml -> CodeBlock.of(
            "%M",
            ClassName("org.springframework.http", "MediaType").member("TEXT_XML"),
        )
    }

    private fun CodeGenTree.Operation.generateInterfaceSpec(): FunSpec {
        val function = FunSpec.builder(name)

        function.receiver(
            ClassName("org.springframework.web.reactive.function.server", "CoRouterFunctionDsl"),
        )

        val documentation = documentation
        if (!documentation.isNullOrBlank()) {
            function.addKdoc(documentation.toKdoc())
        }
        if (deprecated) {
            function.addAnnotation(
                AnnotationSpec.builder(ClassName("kotlin", "Deprecated"))
                    .addMember("message = %S", "")
                    .build(),
            )
        }

        val inputContentType = inputContentType
        val inputWrapperType = inputWrapperType
        val input = input

        val returnType = outputWrapperType?.toSpringPoetType(read = false) ?: output?.toSpringPoetType(read = false)

        function.addParameter(
            "action",
            LambdaTypeName.get(
                receiver = ClassName("org.springframework.web.reactive.function.server", "ServerRequest"),
                parameters = buildList {
                    if (inputWrapperType != null) {
                        add(
                            ParameterSpec.builder(
                                name = "input",
                                type = inputWrapperType.toSpringPoetType(read = true),
                            ).build(),
                        )
                    } else if (input != null) {
                        add(
                            ParameterSpec.builder(
                                name = "input",
                                type = input.toSpringPoetType(read = true),
                            ).build(),
                        )
                    }
                },
                returnType = when {
                    success == StatusCode.Created && returnType != null -> ClassName(
                        "kotlin",
                        "Pair",
                    ).parameterizedBy(
                        returnType,
                        ClassName("java.net", "URI"),
                    )

                    returnType != null -> returnType

                    else -> UNIT
                },
            ).copy(suspending = true),
        )

        val path = path
        if (path != null) {
            function.beginControlFlow("path(pattern = %P).nest", path)
        }
        if (inputContentType != null) {
            function.beginControlFlow("contentType(%L).nest", inputContentType.toMediaType())
        }
        val outputContentType = outputContentType
        if (outputContentType != null) {
            function.beginControlFlow("accept(%L).nest", outputContentType.toMediaType())
        }

        val soapAction = soapAction
        if (soapAction != null) {
            function.beginControlFlow(
                "%M { it.firstHeader(%S) == %P }.nest {",
                MemberName(
                    "org.springframework.web.reactive.function.server.RequestPredicates",
                    "headers",
                    isExtension = true,
                ),
                "SOAPAction",
                soapAction,
            )
        }

        function.beginControlFlow(
            "method(%L) { request ->",
            method.toSpring(),
        )

        if (input != null && inputContentType?.supportedBySerialization() != false) {
            function.addStatement(
                "val body = request.%M<%T>()",
                MemberName("org.springframework.web.reactive.function.server", "awaitBody", isExtension = true),
                inputWrapperType?.toSpringPoetType(read = true) ?: input.toSpringPoetType(read = false),
            )
        }

        function.addStatement(
            "%Lrequest.action(%L)",
            if (output != null) {
                if (success == StatusCode.Created) {
                    CodeBlock.of("val (response, location) = ")
                } else {
                    CodeBlock.of("val response = ")
                }
            } else {
                CodeBlock.of("")
            },
            if (input != null && inputContentType != null) {
                CodeBlock.of("body")
            } else {
                CodeBlock.of("")
            },
        )

        if (output != null) {
            function.addStatement(
                "%L.%M(response)",
                success.toHttpCode(),
                MemberName(
                    "org.springframework.web.reactive.function.server",
                    "bodyValueAndAwait",
                    isExtension = true,
                ),
            )
        } else {
            function.addStatement(
                "%L.%M()",
                success.toHttpCode(),
                MemberName(
                    "org.springframework.web.reactive.function.server",
                    "buildAndAwait",
                    isExtension = true,
                ),
            )
        }

        function.endControlFlow()

        if (soapAction != null) {
            function.endControlFlow()
        }
        if (inputContentType != null) {
            function.endControlFlow()
        }
        if (outputContentType != null) {
            function.endControlFlow()
        }
        if (path != null) {
            function.endControlFlow()
        }

        return function.build()
    }
}

private fun HttpMethod.toSpring(): CodeBlock {
    val httpMethodClassName = ClassName(
        "org.springframework.http",
        "HttpMethod",
    )

    return when (this) {
        HttpMethod.Head -> CodeBlock.of("%M", httpMethodClassName.member("HEAD"))
        HttpMethod.Get -> CodeBlock.of("%M", httpMethodClassName.member("GET"))
        HttpMethod.Post -> CodeBlock.of("%M", httpMethodClassName.member("POST"))
        HttpMethod.Put -> CodeBlock.of("%M", httpMethodClassName.member("PUT"))
        HttpMethod.Patch -> CodeBlock.of("%M", httpMethodClassName.member("PATCH"))
        HttpMethod.Delete -> CodeBlock.of("%M", httpMethodClassName.member("DELETE"))
        HttpMethod.Options -> CodeBlock.of("%M", httpMethodClassName.member("OPTIONS"))
        HttpMethod.Trace -> CodeBlock.of("%M", httpMethodClassName.member("TRACE"))
        HttpMethod.Query -> CodeBlock.of("%M(%S)", httpMethodClassName.member("valueOf"), "QUERY")
    }
}

private fun CodeGenTree.Type.toSpringPoetType(
    read: Boolean,
): TypeName = when (this) {
    CodeGenTree.Type.Builtin.FILE,
    CodeGenTree.Type.Builtin.BYTEARRAY,
    -> if (read) {
        ClassName("java.io", "InputStream")
    } else {
        ClassName("org.springframework.web.servlet.mvc.method.annotation", "StreamingResponseBody")
    }

    else -> toPoetType()
}

private fun ContentType.supportedBySerialization() = when (this) {
    ContentType.ApplicationSoapXml -> true
    ContentType.ApplicationXml -> true
    ContentType.ApplicationJson -> true
    ContentType.ApplicationProblemJson -> true
    ContentType.ApplicationProblemXml -> true
    ContentType.FormUrlEncoded -> false
    ContentType.MultipartFormData -> false
    ContentType.OctetStream -> false
    ContentType.TextPlain -> true
    ContentType.ApplicationZip -> false
    ContentType.TextCsv -> true
    ContentType.TextXml -> true
    is ContentType.Custom -> false
}
