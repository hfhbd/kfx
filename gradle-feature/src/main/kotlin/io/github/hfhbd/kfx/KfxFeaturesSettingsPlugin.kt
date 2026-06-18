package io.github.hfhbd.kfx

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.features.annotations.RegistersProjectFeatures

@RegistersProjectFeatures(
    KfxFeature::class,
)
abstract class KfxFeaturesSettingsPlugin : Plugin<Settings> {
    override fun apply(target: Settings) = Unit
}
