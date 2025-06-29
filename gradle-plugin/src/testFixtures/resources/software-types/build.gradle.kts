plugins {
    alias(libs.plugins.kotlin.jvm)
    id("kfx")
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.serialization.json)
}

kfx.grip {
    packageName.set(null)

    sourceSets.main {
        usingSourceSet(kotlin)
    }
}
