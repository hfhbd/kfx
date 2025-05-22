package io.github.hfhbd.kfx.swagger

import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.ir.PackageName
import io.github.hfhbd.kfx.toCodeGen
import org.gradle.api.file.*
import org.gradle.api.provider.Property
import org.gradle.workers.*
import java.util.*

internal abstract class SwaggerGeneration : WorkAction<SwaggerGeneration.SwaggerParameters> {
    interface SwaggerParameters : WorkParameters {
        val packageName: Property<String>
        val swaggerFile: RegularFileProperty
        val outputFolder: DirectoryProperty
    }

    override fun execute() {
        val firTransformers = ServiceLoader.load(SwaggerTransformer::class.java).toList()
        val transformerFactories = ServiceLoader.load(IrTransformer::class.java).toList()
        val codeGenCreator = ServiceLoader.load(CodeGenCreator::class.java).single()
        val codeGenTransformer = ServiceLoader.load(CodeGenTransformer::class.java).toList()
        val irTree = parameters.swaggerFile.asFile.get().toPath().createIr(
            firTransformers,
        )
        val packageName = parameters.packageName.orNull
        val codeGenerator = irTree.toCodeGen(
            if (packageName != null) {
                listOf(PackageName(packageName)) + transformerFactories
            } else {
                transformerFactories
            },
            codeGenCreator,
            codeGenTransformer,
        )
        val codeGenerators = ServiceLoader.load(CodeGenerator::class.java)
        for (codeGeneratorFactory in codeGenerators) {
            codeGeneratorFactory.generate(codeGenerator, parameters.outputFolder.asFile.get().toPath())
        }
    }
}
