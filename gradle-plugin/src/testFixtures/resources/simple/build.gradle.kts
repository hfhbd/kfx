plugins {
    alias(libs.plugins.kotlin.jvm)
    id("kfx")
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.datetime)
    implementation(libs.serialization.json)
}

kfx.named("grip") {
    sourceSets.main {
        usingSourceSet(kotlin)
    }
}
