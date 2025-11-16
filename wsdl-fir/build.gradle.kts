plugins {
    id("compilerModule")
}

dependencies {
    api(projects.core)
    api(projects.wsdlModel)

    testFixturesApi(libs.ktor.client.core)
    testFixturesApi(projects.ktorServerSoapPlugin)
    testFixturesApi(libs.ktor.server.core)
    testFixturesApi(testFixtures(projects.core))
}

testing.suites {
    withType(JvmTestSuite::class) {
        useKotlinTest()

        dependencies {
            implementation(testFixtures(project()))

            implementation(projects.kotlin)
            implementation(projects.creatorXmlutil)
            implementation(projects.ktorClient)
            implementation(projects.ktorServer)
            implementation(projects.soapRuntime)
            implementation(projects.soap11)
            implementation(projects.responseClasses)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.server.core)

            implementation(projects.validation)

            implementation(testFixtures(projects.wsdlModel))
        }
    }

    register("FooService", JvmTestSuite::class) {
        dependencies {
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.server.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.server.test.host)
            implementation(libs.ktor.client.logging)
        }
    }

    register("IncludedService", JvmTestSuite::class) {
        dependencies {
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.server.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.server.test.host)
            implementation(libs.ktor.client.logging)
        }
    }
}
