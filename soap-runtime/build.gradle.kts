plugins {
    id("runtimeModule")
}

kotlin {
    wasmWasi {
        nodejs()
    }

    sourceSets.commonMain {
        dependencies {
            api(libs.serialization.core)
            api(libs.serialization.xml)
            api(libs.datetime)
        }
    }
}
