plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("setup")
    id("publish")
}

kotlin {
    jvmToolchain(8)

    jvm()
    js {
        nodejs()
    }
    wasmJs {
        nodejs()
    }

    // tier 1
    linuxX64()
    macosX64()
    macosArm64()
    iosSimulatorArm64()
    iosX64()

    // tier 2
    linuxArm64()
    watchosSimulatorArm64()
    watchosX64()
    watchosArm32()
    watchosArm64()
    tvosSimulatorArm64()
    tvosX64()
    tvosArm64()
    iosArm64()

    // tier 3
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()
    mingwX64()
    watchosDeviceArm64()

    explicitApi()
    compilerOptions {
        progressiveMode.set(true)
        optIn.add("kotlin.uuid.ExperimentalUuidApi")
    }

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

val emptyJar by tasks.registering(Jar::class)

publishing.publications.withType<MavenPublication>().configureEach {
    artifact(emptyJar) {
        classifier = "javadoc"
    }
}

// https://youtrack.jetbrains.com/issue/KT-46466
val signingTasks = tasks.withType<Sign>()
tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOn(signingTasks)
}

plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsPlugin> {
    the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsEnvSpec>().downloadBaseUrl = null
}
plugins.withType<org.jetbrains.kotlin.gradle.targets.wasm.nodejs.WasmNodeJsPlugin> {
    the<org.jetbrains.kotlin.gradle.targets.wasm.nodejs.WasmNodeJsEnvSpec>().downloadBaseUrl = null
}
