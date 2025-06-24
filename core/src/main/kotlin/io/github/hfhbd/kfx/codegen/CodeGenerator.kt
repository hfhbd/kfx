package io.github.hfhbd.kfx.codegen

import java.nio.file.Path

fun interface CodeGenerator {
    fun generate(codeGenTree: CodeGenTree, outputFolder: Path)
}
