package io.github.hfhbd.kfx

import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.create
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.IrTransformer

public fun IRTree.toCodeGen(
    transformers: List<IrTransformer>,
    codeGenCreator: CodeGenCreator,
    codeGenTransformers: List<CodeGenTransformer>,
): CodeGenTree {
    var irTree = this
    for (transformer in transformers) {
        irTree = transformer.invoke(irTree)
    }
    var codeGenTree = codeGenCreator.create(irTree)
    for (codeGenTransformer in codeGenTransformers) {
        codeGenTree = codeGenTransformer(codeGenTree, irTree)
    }
    return codeGenTree
}
