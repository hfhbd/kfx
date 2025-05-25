plugins {
    id("runtimeModule")
}

kotlin.sourceSets.commonMain {
    dependencies {
        api(libs.serialization.core)
        api(libs.ktor.server.core)
    }
}

licensee {
    allow("MIT")
}
