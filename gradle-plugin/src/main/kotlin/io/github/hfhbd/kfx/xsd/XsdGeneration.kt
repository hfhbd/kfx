package io.github.hfhbd.kfx.xsd

import io.github.hfhbd.kfx.xsd.fir.generateXsd
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters

internal abstract class XsdGeneration : WorkAction<XsdGeneration.XsdParameters> {
    interface XsdParameters : WorkParameters {
        val xsdFiles: ConfigurableFileCollection
        val outputDirectory: DirectoryProperty
    }

    override fun execute() {
        for (xsdFile in parameters.xsdFiles) {
            xsdFile.inputStream().use {
                generateXsd(
                    xsdFile = it,
                    import = { fileName ->
                        parameters.xsdFiles.singleOrNull { it.name == fileName }?.inputStream() ?: error(
                            "Expected $fileName in ${parameters.xsdFiles.map { it.name }}",
                        )
                    },
                    outputDirectory = parameters.outputDirectory.asFile.get().toPath(),
                )
            }
        }
    }
}
