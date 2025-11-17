plugins {
    id("compilerModule")
}

dependencies {
    api(projects.core)
    api(projects.xsdModel)

    testFixturesImplementation(testFixtures(projects.core))
}

testing.suites {
    withType(JvmTestSuite::class).configureEach {
        dependencies {
            implementation(testFixtures(project()))

            implementation(projects.kotlin)
            implementation(projects.creatorXmlutil)
            implementation(projects.validation)

            implementation(testFixtures(projects.xsdModel))
        }
    }

    register("gradleDependencyVerification", JvmTestSuite::class) {
        dependencies {
            implementation(projects.contextualDate)
        }
    }
    register("bar", JvmTestSuite::class)
}
