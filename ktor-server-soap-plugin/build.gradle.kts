plugins {
    id("runtimeModule")
}

kotlin.sourceSets {
    commonMain {
        dependencies {
            api(libs.ktor.server.core)
        }
    }
}

licensee {
    allowUrl("https://opensource.org/license/mit") {
        because("MIT")
    }
}
