package io.github.hfhbd.kfx

import app.softwork.serviceloader.*
import com.squareup.kotlinpoet.*
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator
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
        addType(generateInterfaceSpec())
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

    private fun CodeGenTree.Operation.generateInterfaceSpec(): TypeSpec {
        val interfaceSpec = TypeSpec.interfaceBuilder(name.replaceFirstChar { it.uppercaseChar() })

        val function = FunSpec.builder(name)
        function.addModifiers(KModifier.ABSTRACT)
        function.addModifiers(KModifier.SUSPEND)

        val documentation = documentation
        if (documentation != null && documentation.isNotBlank()) {
            function.addKdoc(documentation.toKdoc())
        }
        if (deprecated) {
            function.addAnnotation(
                AnnotationSpec.builder(ClassName("kotlin", "Deprecated"))
                    .addMember("message = %S", "")
                    .build(),
            )
        }

        val inputWrapperType = inputWrapperType
        val input = input
        val inputContentType = inputContentType

        if (inputWrapperType != null) {
            function.addParameter(
                name = "input",
                type = inputWrapperType.toSpringPoetType(read = true),
            )
        } else if (input != null) {
            function.addParameter(
                name = "input",
                type = input.toSpringPoetType(read = true),
            )
        }

        function.returns(
            outputWrapperType?.toSpringPoetType(read = false) ?: output?.toSpringPoetType(read = false) ?: UNIT,
        )

        val path = path
        val methodMapping: ClassName = when (method) {
            CodeGenTree.Operation.HttpMethod.Head ->
                ClassName("org.springframework.web.bind.annotation", "RequestMapping")

            CodeGenTree.Operation.HttpMethod.Get ->
                ClassName("org.springframework.web.bind.annotation", "GetMapping")

            CodeGenTree.Operation.HttpMethod.Post ->
                ClassName("org.springframework.web.bind.annotation", "PostMapping")

            CodeGenTree.Operation.HttpMethod.Put ->
                ClassName("org.springframework.web.bind.annotation", "PutMapping")

            CodeGenTree.Operation.HttpMethod.Patch ->
                ClassName("org.springframework.web.bind.annotation", "PatchMapping")

            CodeGenTree.Operation.HttpMethod.Delete ->
                ClassName("org.springframework.web.bind.annotation", "DeleteMapping")
        }

        function.addAnnotation(
            AnnotationSpec.builder(
                methodMapping,
            ).addMember(
                "name = %S",
                name,
            ).apply {
                if (path != null) {
                    addMember("path = [%S]", path)
                }

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

        interfaceSpec.addFunction(function.build())
        val type = interfaceSpec.build()
        return type
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
