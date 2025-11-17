package io.github.hfhbd.kfx.xsd

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import java.io.InputStream

internal abstract class XsdGeneration : WorkAction<XsdGeneration.XsdParameters> {
    interface XsdParameters : WorkParameters {
        val xsdFiles: ConfigurableFileCollection
        val outputDirectory: DirectoryProperty
    }

    override fun execute() {
        val openStreams = mutableMapOf<String, InputStream>()

        try {
            for (xsdFile in parameters.xsdFiles) {
                if (xsdFile.nameWithoutExtension !in openStreams) {
                    xsdFile.inputStream().use {
                        openStreams[xsdFile.nameWithoutExtension] = it
                        generateXsd(
                            xsdFile = it,
                            import = { fileName ->
                                val inputStream =
                                    parameters.xsdFiles.singleOrNull { it.name == fileName }?.inputStream() ?: error(
                                        "Expected $fileName in ${parameters.xsdFiles.map { it.name }}",
                                    )
                                openStreams[fileName] = inputStream
                                inputStream
                            },
                            outputDirectory = parameters.outputDirectory.asFile.get().toPath(),
                        )
                    }
                }
            }
        } finally {
            for (openStream in openStreams.values) {
                openStream.close()
            }
        }
    }
}
