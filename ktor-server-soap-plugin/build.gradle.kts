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
    jvmTest {
        dependencies {
            implementation(libs.ktor.server.test.host)
            implementation(libs.ktor.server.content.negotiation)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.xml)
            implementation(libs.ktor.server.double.receive)
           // implementation(testFixtures(projects.wsdlFir))

           // implementation(testFixtures(projects.core))
            implementation(libs.serialization.json)
            implementation(projects.wsdlModel)
            implementation(libs.ktor.client.cio)
        }
    }
}

licensee {
    allow("MIT")
}
