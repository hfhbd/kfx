package io.github.hfhbd.kfx.swagger

import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.ir.PackageName
import org.gradle.api.file.*
import org.gradle.api.provider.Property
import org.gradle.workers.*
import java.util.ServiceLoader

internal abstract class SwaggerGeneration : WorkAction<SwaggerGeneration.SwaggerParameters> {
    interface SwaggerParameters : WorkParameters {
        val packageName: Property<String>
        val swaggerFile: RegularFileProperty
        val outputFolder: DirectoryProperty
    }

    override fun execute() {
        val packageName = parameters.packageName.orNull
        val transformerFactories = ServiceLoader.load(IrTransformer::class.java)

        generate(
            swaggerFile = parameters.swaggerFile.asFile.get(),
            outputFolder = parameters.outputFolder.asFile.get(),
            transformerFactories = if (packageName != null) {
                listOf(PackageName(packageName)) + transformerFactories
            } else {
                transformerFactories
            },
        )
    }
}
