plugins {
    id("runtimeModule")
}

kotlin {
    wasmWasi {
        nodejs()
    }

    sourceSets.commonMain {
        dependencies {
            api(libs.serialization.json)
        }
    }
}
