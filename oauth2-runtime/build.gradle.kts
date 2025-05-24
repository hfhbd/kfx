plugins {
    id("runtimeModule")
}

kotlin.sourceSets.commonMain {
    dependencies {
        api(libs.serialization.json)
    }
}
