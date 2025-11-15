package io.github.hfhbd.kfx.plugins.soap11

import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.IrTransformer

@ServiceLoader(IrTransformer::class)
class Soap11FaultTransformer : IrTransformer {
    override fun invoke(irTree: IRTree): IRTree = irTree.copy(
        operations = irTree.operations.mapTo(mutableSetOf()) {
            it.copy(
                fault = it.fault ?: IRTree.NormalClass(
                    packageName = "io.github.hfhbd.kfx.soap11",
                    name = "Fault",
                    packageNameSuffix = "",
                    serialName = null,
                    namespace = null,
                    members = emptyMap(),
                    documentation = null,
                    isFault = true,
                    discriminator = null,
                    allOf = null,
                    deprecated = false,
                ),
            )
        },
    )
}

@ServiceLoader(CodeGenTransformer::class)
class Soap11Transformer : CodeGenTransformer {
    override fun invoke(codeGen: CodeGenTree, ir: IRTree): CodeGenTree = codeGen.copy(
        operations = codeGen.operations.mapTo(mutableSetOf()) {
            it.addSoapWrapper()
        },
    )

    private fun envelope(type: CodeGenTree.Type) = CodeGenTree.NormalClass(
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

    private fun CodeGenTree.Operation.addSoapWrapper(): CodeGenTree.Operation = copy(
        inputWrapper = CodeGenTree.Expression.Create(
            envelope(input!!),
            listOf(
                "header" to CodeGenTree.Expression.NullLiteral,
                "body" to CodeGenTree.Expression.Input,
            ),
        ),
        inputWrapperType = envelope(input!!),
        outputWrapperType = envelope(output!!),
        outputMember = CodeGenTree.Expression.Chain(
            CodeGenTree.Expression.Output,
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
        faultWrapper = fault?.let { envelope(it) },
    )
}
