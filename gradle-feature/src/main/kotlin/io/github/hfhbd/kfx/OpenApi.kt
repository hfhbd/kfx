package io.github.hfhbd.kfx

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.provider.Property

interface OpenApi : Kfx {
    val files: ConfigurableFileCollection

    val packageName: Property<String>
}
