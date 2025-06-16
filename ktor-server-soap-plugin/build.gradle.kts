plugins {
    id("runtimeModule")
}

kotlin.sourceSets {
    commonMain {
        dependencies {
            api(libs.ktor.server.core)
            api(libs.serialization.xml)
        }
    }
}

licensee {
    allowUrl("https://opensource.org/license/mit") {
        because("MIT")
    }
}
