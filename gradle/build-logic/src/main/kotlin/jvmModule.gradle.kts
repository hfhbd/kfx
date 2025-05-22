plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("setup")
    id("publish")
}

kotlin {
    jvmToolchain(8)

    compilerOptions {
        optIn.add("kotlin.uuid.ExperimentalUuidApi")
    }
}

publishing {
    publications.register<MavenPublication>("gpr") {
        from(components["java"])
    }
}
