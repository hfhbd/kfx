package io.github.hfhbd.kfx.wsdl

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import java.io.InputStream

internal abstract class WsdlGeneration : WorkAction<WsdlGeneration.WsdlParameters> {
    interface WsdlParameters : WorkParameters {
        val wsdlFile: RegularFileProperty
        val schemaFiles: ConfigurableFileCollection
        val outputDirectory: DirectoryProperty
    }

    override fun execute() {
        parameters.wsdlFile.asFile.get().inputStream().use {
            val openStreams = mutableListOf<InputStream>()
            try {
                generate(
                    wsdlFile = it,
                    import = { fileName ->
                        val inputStream = parameters.schemaFiles.singleOrNull { it.name == fileName }?.inputStream() ?: error(
                            "Expected $fileName in ${parameters.schemaFiles.map { it.name }}",
                        )
                        openStreams.add(inputStream)
                        inputStream
                    },
                    outputDirectory = parameters.outputDirectory.asFile.get().toPath(),
                )
            } finally {
                for (openStream in openStreams) {
                    openStream.close()
                }
            }
        }
    }
}
