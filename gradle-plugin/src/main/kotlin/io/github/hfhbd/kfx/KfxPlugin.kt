package io.github.hfhbd.kfx

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class KfxPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create(KfxExtension::class.java, "kfx", KfxExtensionImpl::class.java)
    }
}
