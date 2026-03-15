package io.github.hfhbd.kfx

import org.gradle.api.file.ConfigurableFileCollection

interface Xsd : Kfx {
    val schemaFiles: ConfigurableFileCollection
}
