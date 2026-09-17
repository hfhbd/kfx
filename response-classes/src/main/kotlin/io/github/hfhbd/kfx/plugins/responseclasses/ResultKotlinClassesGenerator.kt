package io.github.hfhbd.kfx.plugins.responseclasses

import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.kotlin.KotlinClassesGenerator
import io.github.hfhbd.serviceloader.ServiceLoader

@ServiceLoader(CodeGenerator::class)
class ResultKotlinClassesGenerator :
    KotlinClassesGenerator(
        include = { it is CodeGenTree.NormalClass && it.isResultClass },
    )
