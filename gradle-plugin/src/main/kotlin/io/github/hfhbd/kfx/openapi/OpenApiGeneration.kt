package io.github.hfhbd.kfx.openapi

import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.ir.PackageName
import org.gradle.api.file.*
import org.gradle.api.provider.Property
import org.gradle.workers.*
import java.util.*

internal abstract class OpenApiGeneration : WorkAction<OpenApiGeneration.OpenApiParameters> {
    interface OpenApiParameters : WorkParameters {
        val openapiFile: RegularFileProperty
        val packageName: Property<String>
        val outputDirectory: DirectoryProperty
    }

    override fun execute() {
        val packageName = parameters.packageName.orNull
        val transformerFactories = ServiceLoader.load(IrTransformer::class.java)

        parameters.openapiFile.asFile.get().inputStream().use {
            generate(
                openApiFile = it,
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
