package io.github.hfhbd.kfx.plugins.soap11

import app.softwork.serviceloader.*
import io.github.hfhbd.kfx.ContentType.*
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.ir.IRTree

@ServiceLoader(CodeGenTransformer::class)
class Soap11Transformer : CodeGenTransformer {
    override fun invoke(codeGen: CodeGenTree, ir: IRTree): CodeGenTree = codeGen.copy(
        operations = codeGen.operations.mapTo(mutableSetOf()) {
            it.addSoapWrapper()
        },
    )

    private val envelopeBody = CodeGenTree.NormalClass(
        packageName = "io.github.hfhbd.kfx.soap11",
        names = listOf("Body"),
        annotations = emptyList(),
        documentation = null,
        types = emptyList(),
        isFault = false,
        members = emptyList(),
        functions = emptyList(),
        isSealed = false,
    )

    private fun CodeGenTree.Operation.addSoapWrapper(): CodeGenTree.Operation {
        fun envelope(type: CodeGenTree.Type) = CodeGenTree.NormalClass(
            packageName = "io.github.hfhbd.kfx.soap11",
            names = listOf("Envelope"),
            annotations = emptyList(),
            documentation = null,
            types = listOf(type),
            isFault = false,
            members = emptyList(),
            functions = emptyList(),
            isSealed = false,
        )

        return CodeGenTree.Operation(
            packageName = packageName,
            name = name,
            documentation = documentation,
            location = location,
            address = address,
            input = input!!,
            output = output!!,
            fault = fault,
            method = CodeGenTree.Operation.HttpMethod.Post,
            parameters = listOf(),
            queryParameters = emptyList(),
            path = null,
            inputContentType = TextXml,
            outputContentType = TextXml,
            inputWrapper = CodeGenTree.Expression.Create(
                envelope(input!!),
                listOf(
                    "header" to CodeGenTree.Expression.NullLiteral,
                    "body" to CodeGenTree.Expression.Create(
                        envelopeBody,
                        listOf(
                            "body" to CodeGenTree.Expression.Input,
                        ),
                    ),
                ),
            ),
            inputWrapperType = envelope(input!!),
            outputWrapperType = envelope(output!!),
            outputMember = CodeGenTree.Expression.Chain(
                CodeGenTree.Expression.Output,
                CodeGenTree.Expression.Chain(
                    CodeGenTree.Expression.CallMember(
                        CodeGenTree.Member(
                            name = "body",
                            type = envelopeBody,
                            nullable = false,
                            documentation = null,
                            annotations = listOf(),
                        ),
                    ),
                    CodeGenTree.Expression.CallMember(
                        CodeGenTree.Member(
                            name = "body",
                            type = output!!,
                            nullable = false,
                            documentation = null,
                            annotations = listOf(),
                        ),
                    ),
                ),
            ),
            faultWrapper = fault?.let { envelope(it) },
            nullableOutput = null,
            success = 200,
            headers = emptyList(),
            deprecated = false,
        )
    }
}
