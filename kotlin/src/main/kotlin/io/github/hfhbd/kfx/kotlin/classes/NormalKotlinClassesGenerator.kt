package io.github.hfhbd.kfx.kotlin.classes

import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.kotlin.KotlinClassesGenerator

@ServiceLoader(CodeGenerator::class)
class NormalKotlinClassesGenerator :
    KotlinClassesGenerator(
        include = { !it.provided },
    )
