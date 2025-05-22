plugins {
    id("jvmModule")
}

dependencies {
    api(libs.serialization.core)
    api(libs.ktor.server.core)
}

licensee {
    allow("MIT")
}
