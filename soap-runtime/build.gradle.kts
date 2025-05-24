plugins {
    id("runtimeModule")
}

kotlin.sourceSets.commonMain {
    dependencies {
        api(libs.serialization.core)
        api(libs.serialization.xml)
        api(libs.datetime)
    }
}
