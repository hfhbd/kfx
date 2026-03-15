package io.github.hfhbd.kfx

import org.gradle.api.Named
import org.gradle.api.tasks.Nested

interface Kfx : Named {
    override fun getName(): String

    @get:Nested
    val dependencies: KfxDependencies
}
