plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.plugins.kotlin.jvm.dep)
    implementation(libs.plugins.kotlin.serialization.dep)
    implementation(libs.plugins.dokka.dep)
    implementation(libs.plugins.licensee.dep)
    implementation(libs.plugins.serviceloader.dep)
    implementation(libs.plugins.detekt.dep)
    implementation(libs.plugins.validation.dep)
    implementation(libs.plugins.mavencentral.dep)
}

val Provider<PluginDependency>.dep: Provider<String> get() = map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }
