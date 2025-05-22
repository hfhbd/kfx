package io.github.hfhbd.kfx.wsdl

import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.toCodeGen
import org.gradle.api.file.*
import org.gradle.workers.*
import java.util.*

internal abstract class WsdlGeneration : WorkAction<WsdlGeneration.WsdlParameters> {
    interface WsdlParameters : WorkParameters {
        val wsdlFile: RegularFileProperty
        val schemaFiles: ConfigurableFileCollection
        val outputFolder: DirectoryProperty
    }

    override fun execute() {
        val firTransformerFactories = ServiceLoader.load(WsdlTransformerFactory::class.java).toList()
        val transformerFactories = ServiceLoader.load(IrTransformer::class.java).toList()
        val codeGenCreator = ServiceLoader.load(CodeGenCreator::class.java).single()
        val codeGenTransformer = ServiceLoader.load(CodeGenTransformer::class.java).toList()
        val schemaFiles = parameters.schemaFiles.files
        val irTree = parameters.wsdlFile.asFile.get().toPath().createIr(
            firTransformerFactories,
        ) { filename: String ->
            val file = schemaFiles.singleOrNull { it.name == filename } ?: error(
                "Expected $filename in ${schemaFiles.map { it.name }}",
            )
            file.toPath()
        }
        val codeGenerator = irTree.toCodeGen(
            transformerFactories,
            codeGenCreator,
            codeGenTransformer,
        )
        val codeGenerators = ServiceLoader.load(CodeGenerator::class.java)
        for (codeGeneratorFactory in codeGenerators) {
            codeGeneratorFactory.generate(codeGenerator, parameters.outputFolder.asFile.get().toPath())
        }
    }
}
