package io.github.hfhbd.kfx.codegen

fun interface CodeGenTransformer {
    operator fun invoke(codeGen: CodeGenTree): CodeGenTree
}
