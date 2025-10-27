package io.github.hfhbd.kfx.kotlin.spring.server

import app.softwork.serviceloader.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.MemberName.Companion.member
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.codegen.CodeGenTree
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
    override fun generateFileSpec(codeGenTree: CodeGenTree): List<FileSpec> {
        return codeGenTree.operations.map { it.generateFileSpec() }
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

    private fun Int?.toHttpCode(): CodeBlock = when (this) {
        null, 200 -> CodeBlock.of("ok()")
        201 -> CodeBlock.of("created(location)")
        202 -> CodeBlock.of("accepted()")
        204 -> CodeBlock.of("noContent()")
        400 -> CodeBlock.of("badRequest()")
        404 -> CodeBlock.of("notFound()")
        else -> error("Not yet supported: $this")
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
                    success == 201 && returnType != null -> ClassName(
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

        val address = address
        if (address != null) {
            function.beginControlFlow(
                "%M { it.firstHeader(%S) == %P }.nest {",
                MemberName(
                    "org.springframework.web.reactive.function.server.RequestPredicates",
                    "headers",
                    isExtension = true,
                ),
                "SOAPAction",
                address,
            )
        }

        function.beginControlFlow(
            "method(%M) { request ->",
            MemberName(
                ClassName(
                    "org.springframework.http",
                    "HttpMethod",
                ),
                when (method) {
                    CodeGenTree.Operation.HttpMethod.Head ->
                        "HEAD"

                    CodeGenTree.Operation.HttpMethod.Get ->
                        "GET"

                    CodeGenTree.Operation.HttpMethod.Post ->
                        "POST"

                    CodeGenTree.Operation.HttpMethod.Put ->
                        "PUT"

                    CodeGenTree.Operation.HttpMethod.Patch ->
                        "PATCH"

                    CodeGenTree.Operation.HttpMethod.Delete ->
                        "DELETE"
                },
            ),
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
                if (success == 201) {
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

        if (address != null) {
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
