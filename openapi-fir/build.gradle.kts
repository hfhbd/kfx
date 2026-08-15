plugins {
    id("compilerModule")
}

dependencies {
    api(projects.core)
    api(projects.openapiModel)

    testFixturesImplementation(testFixtures(projects.core))
}

testing.suites {
    withType(JvmTestSuite::class).configureEach {
        dependencies {
            implementation(testFixtures(project()))

            implementation(projects.creatorKotlinxjson)
            implementation(projects.ktorClient)
            implementation(projects.ktorServer)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.server.core)
            implementation(projects.oauth2Runtime)
            implementation(testFixtures(projects.openapiModel))
        }
    }

    register("a", JvmTestSuite::class) {
        dependencies {
            implementation(projects.kotlin)
            implementation(projects.responseClasses)
            implementation(projects.validation)
        }
    }
    register("results", JvmTestSuite::class) {
        dependencies {
            implementation(projects.responseClasses)
        }
    }
    register("sealed", JvmTestSuite::class) {
        dependencies{
            implementation(projects.kotlin)
        }
    }

    register("jira", JvmTestSuite::class) {
        dependencies {
            implementation(projects.kotlin)
            implementation(projects.irPackagename)
        }
    }
    register("central", JvmTestSuite::class) {
        dependencies {
            implementation(projects.kotlin)
            implementation(projects.irPackagename)
        }
    }
}
