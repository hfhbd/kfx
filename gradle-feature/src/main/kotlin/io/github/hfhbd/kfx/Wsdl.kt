package io.github.hfhbd.kfx

import org.gradle.api.file.ConfigurableFileCollection

interface Wsdl : Kfx {
    val wsdlFiles: ConfigurableFileCollection
    val schemaFiles: ConfigurableFileCollection
}
