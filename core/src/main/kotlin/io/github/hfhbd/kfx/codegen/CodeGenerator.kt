package io.github.hfhbd.kfx.codegen

import java.io.File

fun interface CodeGenerator {
    fun generate(codeGenTree: CodeGenTree, outputFolder: File)
}
