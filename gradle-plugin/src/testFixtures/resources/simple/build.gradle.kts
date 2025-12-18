plugins {
    alias(libs.plugins.kotlin.jvm)
    id("kfx")
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.serialization.json)
}

kfx.grip {
    files.from(file("grip.json"))

    packageName.set(null)

    usingKotlinSourceSet(kotlin.sourceSets.main)
}
