plugins {
    id("compilerModule")
}

dependencies {
    api(projects.core)
    api(projects.openapiModel)

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
            implementation(testFixtures(projects.openapiModel))
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

    register("a", JvmTestSuite::class)
    register("sealed", JvmTestSuite::class)

    register("jira", JvmTestSuite::class) {
        dependencies {
            implementation(projects.irPackagename)
        }
    }
    register("central", JvmTestSuite::class) {
        dependencies {
            implementation(projects.irPackagename)
        }
    }
}
