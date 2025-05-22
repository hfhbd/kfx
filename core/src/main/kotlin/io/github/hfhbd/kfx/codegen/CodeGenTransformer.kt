package io.github.hfhbd.kfx.codegen

import io.github.hfhbd.kfx.ir.IRTree

fun interface CodeGenTransformer {
    operator fun invoke(codeGen: CodeGenTree, ir: IRTree): CodeGenTree
}
