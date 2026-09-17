package io.github.hfhbd.kfx.kotlin.classes

import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.kotlin.KotlinClassesGenerator
import io.github.hfhbd.serviceloader.ServiceLoader

@ServiceLoader(CodeGenerator::class)
class NormalKotlinClassesGenerator :
    KotlinClassesGenerator(
        include = { !it.provided },
    )
