plugins {
    id("compilerModule")
}

dependencies {
    api(projects.core)
    api(projects.swaggerModel)

    testFixturesImplementation(testFixtures(projects.core))
}

testing.suites {
    withType(JvmTestSuite::class) {
        useKotlinTest()

        dependencies {
            implementation(testFixtures(project()))

            implementation(projects.kotlin)
            implementation(projects.creatorKotlinxjson)
            implementation(projects.ktorClient)
            implementation(projects.ktorServer)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.server.core)
            implementation(projects.oauth2Runtime)

            implementation(testFixtures(projects.swaggerModel))
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

    register("leanix", JvmTestSuite::class) {
        dependencies {
            implementation(projects.irPackagename)
            implementation(projects.oauth2Runtime)
            implementation(projects.validation)
        }
    }
    register("sapci", JvmTestSuite::class) {
        dependencies {
            implementation(projects.irPackagename)
            implementation(projects.irOdata)
        }
    }
}
