plugins {
    id("org.jetbrains.dokka")
    id("merge-detekt")
}

dokka {
    dokkaPublications.configureEach {
        includes.from("README.md")
    }

    dependencies {
        for (project in subprojects) {
            dokka(project.path)
        }
        dokkaPlugin(libs.dokka.mermaid)
    }
}

mergeDetekt {
    dependencies {
        for (subproject in subprojects) {
            sarif(project(subproject.path))
        }
    }
}

plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsEnvSpec>().downloadBaseUrl = null
}
plugins.withType<org.jetbrains.kotlin.gradle.targets.wasm.nodejs.WasmNodeJsRootPlugin> {
    the<org.jetbrains.kotlin.gradle.targets.wasm.nodejs.WasmNodeJsEnvSpec>().downloadBaseUrl = null
}
