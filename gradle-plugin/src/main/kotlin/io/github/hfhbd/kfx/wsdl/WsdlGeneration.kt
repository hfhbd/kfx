package io.github.hfhbd.kfx.wsdl

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters

internal abstract class WsdlGeneration : WorkAction<WsdlGeneration.WsdlParameters> {
    interface WsdlParameters : WorkParameters {
        val wsdlFile: RegularFileProperty
        val schemaFiles: ConfigurableFileCollection
        val outputFolder: DirectoryProperty
    }

    override fun execute() {
        generate(
            wsdlFile = parameters.wsdlFile.asFile.get(),
            schemaFiles = parameters.schemaFiles.files,
            outputFolder = parameters.outputFolder.asFile.get(),
        )
    }
}
