plugins {
    `kotlin-dsl`
    id("setup")
    id("publish")
}

dependencies {
    compileOnly(projects.wsdlFir)
    compileOnly(projects.swaggerFir)
    compileOnly(projects.irPackagename)
    compileOnly(projects.openapiFir)
}

tasks.validatePlugins {
    enableStricterValidation.set(true)
}

configurations.configureEach {
    if (isCanBeConsumed) {
        attributes {
            attribute(
                GradlePluginApiVersion.GRADLE_PLUGIN_API_VERSION_ATTRIBUTE,
                objects.named<GradlePluginApiVersion>(GradleVersion.version("8.11").version)
            )
        }
    }
}

val storeVersion by tasks.registering(StoreVersion::class)
sourceSets.main {
    kotlin.srcDir(storeVersion)
}

gradlePlugin.plugins.configureEach {
    displayName = "kfx Gradle Plugin"
    description = "kfx Gradle Plugin"
}

testing.suites.register("integrationTest", JvmTestSuite::class) {
    useKotlinTest()

    dependencies {
        implementation(testFixtures(project()))
        implementation(gradleTestKit())
        implementation(testFixtures(projects.openapiModel))
    }

    gradlePlugin.testSourceSet(sources)
    
    targets.configureEach {
        testTask {
            environment("fixtureDir", project.file("src/testFixtures").path)
        }
    }
}
