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

    testImplementation(projects.validation)
    testImplementation(projects.ktorClient)
    testImplementation(projects.ktorServer)
    testImplementation(projects.kotlin)
    testImplementation(projects.contextualDate)
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

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.server.core)

            implementation(testFixtures(projects.wsdlModel))
        }
        targets.configureEach {
            tasks.check {
                dependsOn(testTask)
            }
            testTask {
                outputs.dir("build/kfx-tests/${this@withType.name}")
            }
        }
    }

    register("FooService", JvmTestSuite::class) {
        dependencies {
            implementation(projects.validation)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.server.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.server.test.host)
        }
    }
}
