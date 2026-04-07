plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("io.github.hfhbd.kfx:gradle-feature")
    implementation(libs.kotlin.gradle.plugin.impl)
}

gradlePlugin.plugins.register("ecosystem") {
    implementationClass = "EcosystemPlugin"
}
