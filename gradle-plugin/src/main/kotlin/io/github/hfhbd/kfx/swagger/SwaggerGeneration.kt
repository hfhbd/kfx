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
        val outputDirectory: DirectoryProperty
    }

    override fun execute() {
        val packageName = parameters.packageName.orNull
        val transformerFactories = ServiceLoader.load(IrTransformer::class.java)

        parameters.swaggerFile.asFile.get().inputStream().use {
            generate(
                swaggerFile = it,
                outputDirectory = parameters.outputDirectory.asFile.get().toPath(),
                transformerFactories = if (packageName != null) {
                    listOf(PackageName(packageName)) + transformerFactories
                } else {
                    transformerFactories
                },
            )
        }
    }
}
