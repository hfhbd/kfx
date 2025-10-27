package io.github.hfhbd.kfx.kotlin.spring.server

import app.softwork.serviceloader.*
import com.squareup.kotlinpoet.*
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

    private fun Int?.toHttpCode(): MemberName {
        val className = ClassName("org.springframework.http", "HttpStatus")
        return when (this) {
            null, 200 -> MemberName(className, "OK")
            201 -> MemberName(className, "CREATED")
            202 -> MemberName(className, "ACCEPTED")
            204 -> MemberName(className, "NO_CONTENT")
            400 -> MemberName(className, "BAD_REQUEST")
            else -> error("Not yet supported: $this")
        }
    }

    private fun CodeGenTree.Operation.generateInterfaceSpec(): FunSpec {
        val function = FunSpec.builder(name)

        function.receiver(
            ClassName("org.springframework.web.reactive.function.server", "CoRouterFunctionDsl")
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

        function.addParameter(
            "action",
            LambdaTypeName.get(
                receiver = ClassName("org.springframework.web.reactive.function.server", "ServerRequest"),
                parameters = buildList {
                    val inputWrapperType = inputWrapperType
                    val input = input
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
                returnType = outputWrapperType?.toSpringPoetType(read = false) ?: output?.toSpringPoetType(read = false)
                ?: UNIT,
            )
        )

        val path = path
        if (path != null) {
            function.beginControlFlow("path(pattern = %S).nest", path)
        }
        if (inputContentType != null) {
            function.beginControlFlow("contentType(%S)", inputContentType)
        }

        function.addAnnotation(
            AnnotationSpec.builder(
                ClassName("org.springframework.web.bind.annotation", "RequestMapping"),
            ).addMember(
                "name = %S",
                name,
            ).apply {
                val requestMethod = ClassName(
                    "org.springframework.web.bind.annotation",
                    "RequestMethod",
                )

                val methodMapping = MemberName(
                    requestMethod,
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
                )
                addMember(
                    "method = [%M]",
                    methodMapping,
                )

                if (inputContentType != null) {
                    addMember(
                        "consumes = [%S]",
                        inputContentType,
                    )
                }
                val outputContentType = outputContentType
                if (outputContentType != null) {
                    addMember(
                        "produces = [%S]",
                        outputContentType,
                    )
                }
            }.build(),
        )

        function.addAnnotation(
            AnnotationSpec.builder(
                ClassName("org.springframework.web.bind.annotation", "ResponseStatus"),
            ).addMember(
                "value = %L",
                success.toHttpCode(),
            )
                .build(),
        )

        return function.build()
    }
}

fun CodeGenTree.Type.toSpringPoetType(
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
