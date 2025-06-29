package io.github.hfhbd.kfx

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.internal.plugins.software.RegistersSoftwareTypes

@RegistersSoftwareTypes(KfxPlugin::class)
abstract class KfxSettingsPlugin : Plugin<Settings> {
    override fun apply(target: Settings) = Unit
}
