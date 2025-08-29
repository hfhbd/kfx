plugins {
    id("compilerModule")
}

dependencies {
    api(projects.core)
    api(projects.wsdlModel)

    testFixturesImplementation(testFixtures(projects.core))
}

testing.suites {
    withType(JvmTestSuite::class) {
        useKotlinTest()

        dependencies {
            implementation(testFixtures(project()))

            implementation(projects.kotlin)
            implementation(projects.creatorKotlinxjson)
            implementation(projects.validation)
            implementation(projects.contextualDate)

            implementation(testFixtures(projects.xsdModel))
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

    register("gradleDependencyVerification", JvmTestSuite::class)
}
