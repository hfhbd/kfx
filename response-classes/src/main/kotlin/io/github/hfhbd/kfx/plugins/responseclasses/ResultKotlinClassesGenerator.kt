package io.github.hfhbd.kfx.plugins.responseclasses

import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.kotlin.KotlinClassesGenerator

@ServiceLoader(CodeGenerator::class)
class ResultKotlinClassesGenerator :
    KotlinClassesGenerator(
        include = { it is CodeGenTree.NormalClass && it.isResultClass },
    )
