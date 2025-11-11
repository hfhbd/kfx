package io.github.hfhbd.kfx.kotlin.ktor.server

import app.softwork.serviceloader.ServiceLoader
import com.squareup.kotlinpoet.*
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
class KtorServerGenerator : KotlinPoetCodeGenerator {
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

    private fun CodeGenTree.Operation.generateFunSpec(): FunSpec {
        val function = FunSpec.builder(name)
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
                    returnType = returnType?.toKtorPoetType(read = false) ?: UNIT,
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
                MemberName("io.github.hfhbd.kfx.soap11", "soapAction", isExtension = true),
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
        val nameAllocator = NameAllocator()
        nameAllocator.newName("input")
        nameAllocator.newName("builder")
        nameAllocator.newName("response")

        val respond = MemberName("io.ktor.server.response", "respond", isExtension = true)
        val responseBranches = responseBranches
        if (responseBranches != null) {
            function.beginControlFlow("when (response)")
            fun a(responseBranch: CodeGenTree.Operation.ResponseBranches.Branch) {
                function.beginControlFlow("is %T ->", responseBranch.isCondition.toPoetType())
                val response = responseBranch.response
                for ((memberName, _) in responseBranch.isCondition.members) {
                    if (memberName == "body") {
                        continue
                    }
                    function.addStatement(
                        "call.response.%M(%S, response.$memberName)",
                        MemberName("io.ktor.server.response", "header", isExtension = true),
                        memberName,
                    )
                }
                if (response != null) {
                    function.addStatement(
                        "call.response.status(%M)",
                        responseBranch.statusCode.toHttpCode(),
                    )
                    function.addStatement("call.%M(%L)", respond, response.toCodeBlock(nameAllocator))
                } else {
                    function.addStatement("call.%M(%M)", respond, responseBranch.statusCode.toHttpCode())
                }
                function.endControlFlow()
            }

            responseBranches.success?.let { a(it) }
            responseBranches.notFound?.let { a(it) }
            responseBranches.fault?.let { a(it) }
            function.endControlFlow()
        } else {
            if (output != null) {
                function.addStatement(
                    "call.response.status(%M)",
                    success.toHttpCode(),
                )
                function.addStatement("call.%M(response)", respond)
            } else {
                function.addStatement("call.%M(%M)", respond, success.toHttpCode())
            }
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

internal fun String.toKtorServer(): String = replace($$"${", "{")
    .replace("'\\\$([^'.])*'".toRegex()) {
        val value = it.groups[1]?.value
        if (value != null) {
            "'{$value}'"
        } else {
            "$"
        }
    }
