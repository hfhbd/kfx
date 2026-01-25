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
    }

    register("leanix", JvmTestSuite::class) {
        dependencies {
            implementation(projects.irPackagename)
            implementation(projects.oauth2Runtime)
            implementation(projects.validation)
        }
    }
    register("idp", JvmTestSuite::class)
    register("sapci", JvmTestSuite::class) {
        dependencies {
            implementation(projects.irPackagename)
            implementation(projects.irOdata)
        }
    }
}
