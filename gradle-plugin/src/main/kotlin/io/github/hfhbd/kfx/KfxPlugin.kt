package io.github.hfhbd.kfx

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.plugins.software.SoftwareType

abstract class KfxPlugin : Plugin<Project> {
    @get:SoftwareType(name = "kfx", disableModelManagement = true)
    abstract val kfx: KfxExtension

    override fun apply(target: Project) {
        target.extensions.create(KfxExtension::class.java, "kfx", KfxExtensionImpl::class.java)
    }
}
