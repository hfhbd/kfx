package io.github.hfhbd.kfx

import app.softwork.serviceloader.*
import com.squareup.kotlinpoet.*
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator
import java.nio.file.*

@ServiceLoader(CodeGenerator::class)
class KtorServerGenerator : KotlinPoetCodeGenerator {
    override fun generate(codeGenTree: CodeGenTree, outputFolder: Path) {
        val files = generateFileSpec(codeGenTree)
        for (file in files) {
            file.writeTo(outputFolder)
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
        fileName = name,
    ).apply {
        addFunction(generateFunSpec())
    }.build()

    private fun CodeGenTree.Operation.HttpMethod.toKtor(): MemberName = MemberName(
        "io.ktor.server.routing",
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

    private fun Int?.toHttpCode(): MemberName {
        val className = ClassName("io.ktor.http", "HttpStatusCode", "Companion")
        return when (this) {
            null, 200 -> MemberName(className, "OK")
            201 -> MemberName(className, "Created")
            202 -> MemberName(className, "Accepted")
            204 -> MemberName(className, "NoContent")
            400 -> MemberName(className, "BadRequest")
            else -> error("Not yet supported: $this")
        }
    }

    private fun CodeGenTree.Operation.generateFunSpec(): FunSpec {
        val function = FunSpec.builder(name)
        val documentation = documentation
        if (documentation != null && documentation.isNotBlank()) {
            function.addKdoc(documentation.toKdoc())
        }
        function.receiver(ClassName("io.ktor.server.routing", "Route"))

        val inputWrapperType = inputWrapperType
        val input = input
        val inputContentType = inputContentType

        function.addParameter(
            ParameterSpec.builder(
                name = "action",
                type = LambdaTypeName.get(
                    receiver = ClassName("io.ktor.server.application", "ApplicationCall"),
                    parameters = buildList {
                        if (inputWrapperType != null) {
                            add(ParameterSpec.unnamed(inputWrapperType.toKtorPoetType(read = true)))
                        } else if (input != null && inputContentType?.supportedBySerialization() != false) {
                            add(ParameterSpec.unnamed(input.toKtorPoetType(read = true)))
                        }
                    },
                    returnType = outputWrapperType?.toKtorPoetType(read = false) ?: output?.toKtorPoetType(read = false)
                        ?: UNIT,
                ).copy(
                    suspending = true,
                ),
            ).build(),
        )

        val path = path
        if (path != null) {
            function.beginControlFlow(
                "%M%L",
                MemberName("io.ktor.server.routing", "route", isExtension = true),
                CodeBlock.of("(path = %P)", path.toKtorServer()),
            )
        }

        if (inputContentType != null) {
            function.beginControlFlow(
                "%M(%L)",
                MemberName("io.ktor.server.routing", "contentType", isExtension = true),
                inputContentType.toKtor(),
            )
        }
        val outputContentType = outputContentType
        if (outputContentType != null) {
            function.beginControlFlow(
                "%M(%L)",
                MemberName("io.ktor.server.routing", "accept", isExtension = true),
                outputContentType.toKtor(),
            )
        }

        val address = address
        if (address != null) {
            function.beginControlFlow(
                "%M(%S)",
                MemberName("io.github.hfhbd.kfx.soap", "soapAction", isExtension = true),
                address,
            )
        }

        function.beginControlFlow(
            "%M",
            method.toKtor(),
        )

        if (input != null && inputContentType?.supportedBySerialization() != false) {
            function.addStatement(
                "val body = call.%M<%T>()",
                MemberName("io.ktor.server.request", "receive", isExtension = true),
                inputWrapperType?.toKtorPoetType(read = true) ?: input.toKtorPoetType(read = true),
            )
        }
        function.addStatement(
            "%Lcall.action(%L)",
            if (output != null) {
                CodeBlock.of("val response = ")
            } else {
                CodeBlock.of("")
            },
            if (input != null && inputContentType?.supportedBySerialization() != false) {
                CodeBlock.of("body")
            } else {
                CodeBlock.of("")
            },
        )

        val respond = MemberName("io.ktor.server.response", "respond", isExtension = true)
        if (output != null) {
            function.addStatement(
                "call.response.status(%M)",
                success.toHttpCode(),
            )
            function.addStatement("call.%M(response)", respond)
        } else {
            function.addStatement("call.%M(%M)", respond, success.toHttpCode())
        }

        function.endControlFlow()
        if (inputContentType != null) {
            function.endControlFlow()
        }
        if (outputContentType != null) {
            function.endControlFlow()
        }
        if (path != null) {
            function.endControlFlow()
        }
        if (address != null) {
            function.endControlFlow()
        }

        return function.build()
    }
}

internal fun String.toKtorServer(): String = replace("\${", "{")
    .replace("'\\\$([^'.])*'".toRegex()) {
        val value = it.groups[1]?.value
        if (value != null) {
            "'{$value}'"
        } else {
            "\$"
        }
    }
