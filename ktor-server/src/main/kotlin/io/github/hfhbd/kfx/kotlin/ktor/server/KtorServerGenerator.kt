package io.github.hfhbd.kfx.kotlin.ktor.server

import app.softwork.serviceloader.ServiceLoader
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.NameAllocator
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.UNIT
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
    override fun generateFileSpec(codeGenTree: CodeGenTree): List<FileSpec> = codeGenTree.operations.map {
        it.generateFileSpec()
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
        val returnType = returnType

        val lambdaReturnType = if (responseBranches == null) {
            outputWrapperType?.toKtorPoetType(false) ?: output?.toKtorPoetType(false)
        } else {
            returnType?.toKtorPoetType(
                read = false,
            )
        } ?: UNIT

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
                    returnType = lambdaReturnType,
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

        val soapAction = soapAction
        if (soapAction != null) {
            function.beginControlFlow(
                "%M(%S)",
                MemberName("io.github.hfhbd.kfx.soap11", "soapAction", isExtension = true),
                soapAction,
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
        val nameAllocator = NameAllocator()
        nameAllocator.newName("input")
        nameAllocator.newName("builder")
        nameAllocator.newName("response")

        val responseBranches = responseBranches
        if (responseBranches != null) {
            function.addStatement(
                "val response = call.action(%L)",
                if (input != null && inputContentType?.supportedBySerialization() != false) {
                    CodeBlock.of("body")
                } else {
                    CodeBlock.of("")
                },
            )
            function.beginControlFlow("when (response)")
            responseBranches.success?.let { a(it, function, nameAllocator) }
            responseBranches.notFound?.let { a(it, function, nameAllocator) }
            a(responseBranches.fault, function, nameAllocator)
            function.endControlFlow()
        } else {
            if (output != null) {
                function.addStatement(
                    "val response = call.action(%L)",
                    if (input != null && inputContentType?.supportedBySerialization() != false) {
                        CodeBlock.of("body")
                    } else {
                        CodeBlock.of("")
                    },
                )
                function.addStatement(
                    "call.response.status(%M)",
                    success.toHttpCode(),
                )
                function.addStatement("call.%M(response)", respond)
            } else {
                function.addStatement(
                    "call.action(%L)",
                    if (input != null && inputContentType?.supportedBySerialization() != false) {
                        CodeBlock.of("body")
                    } else {
                        CodeBlock.of("")
                    },
                )
                function.addStatement("call.%M(%M)", respond, success.toHttpCode())
            }
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

private val respond = MemberName("io.ktor.server.response", "respond", isExtension = true)

private fun a(
    responseBranch: CodeGenTree.Operation.ResponseBranches.Branch,
    function: FunSpec.Builder,
    nameAllocator: NameAllocator,
) {
    function.beginControlFlow("is %T ->", responseBranch.isCondition.toPoetType())
    val response = responseBranch.response
    for ((headerName, member) in responseBranch.headers) {
        val (memberName, nullable) = member
        if (nullable) {
            function.beginControlFlow("if (response.%L != null)", memberName)
        }
        function.addStatement(
            "call.response.%M(%S, response.$memberName)",
            MemberName("io.ktor.server.response", "header", isExtension = true),
            headerName,
        )
        if (nullable) {
            function.endControlFlow()
        }
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

internal fun String.toKtorServer(): String = replace($$"${", "{")
    .replace("'\\\$([^'.])*'".toRegex()) {
        val value = it.groups[1]?.value
        if (value != null) {
            "'{$value}'"
        } else {
            "$"
        }
    }
