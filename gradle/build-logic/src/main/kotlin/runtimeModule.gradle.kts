plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("setup")
}

kotlin {
    jvmToolchain(8)
    compilerOptions {
        freeCompilerArgs.add("-Xreturn-value-checker=full")
    }

    jvm()
    js {
        nodejs()
    }
    wasmJs {
        nodejs()
    }

    // tier 1
    macosArm64()
    iosSimulatorArm64()
    iosArm64()

    // tier 2
    linuxX64()
    linuxArm64()
    watchosSimulatorArm64()
    watchosArm32()
    watchosArm64()
    tvosSimulatorArm64()
    tvosArm64()

    // tier 3
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()
    mingwX64()
    watchosDeviceArm64()
    iosX64()

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

val emptyJar = tasks.register("emptyJar", Jar::class)

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
