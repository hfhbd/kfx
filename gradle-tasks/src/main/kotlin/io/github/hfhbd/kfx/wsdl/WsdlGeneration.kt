package io.github.hfhbd.kfx.wsdl

import io.github.hfhbd.kfx.wsdl.fir.generateWsdl
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters

internal abstract class WsdlGeneration : WorkAction<WsdlGeneration.WsdlParameters> {
    interface WsdlParameters : WorkParameters {
        val wsdlFile: RegularFileProperty
        val schemaFiles: ConfigurableFileCollection
        val outputDirectory: DirectoryProperty
    }

    override fun execute() {
        parameters.wsdlFile.asFile.get().inputStream().use {
            generateWsdl(
                wsdlFile = it,
                import = { fileName ->
                    parameters.schemaFiles.singleOrNull { it.name == fileName }?.inputStream() ?: error(
                        "Expected $fileName in ${parameters.schemaFiles.map { it.name }}",
                    )
                },
                outputDirectory = parameters.outputDirectory.asFile.get().toPath(),
            )
        }
    }
}
