plugins {
    id("java-gradle-plugin")
    kotlin("jvm")
    id("setup")
    id("publish")
    id("java-test-fixtures")
}

kotlin.jvmToolchain(8)

java {
    withSourcesJar()
    withJavadocJar()
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

configurations.apiElements {
    attributes {
        attribute(
            GradlePluginApiVersion.GRADLE_PLUGIN_API_VERSION_ATTRIBUTE,
            objects.named<GradlePluginApiVersion>(GradleVersion.version("8.11").version)
        )
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

gradlePlugin.plugins.register("kfx") {
    id = "io.github.hfhbd.kfx"
    implementationClass = "io.github.hfhbd.kfx.KfxPlugin"
}

testing.suites {
    withType(JvmTestSuite::class).configureEach {
        useKotlinTest()
    }

    register("integrationTest", JvmTestSuite::class) {
        dependencies {
            implementation(testFixtures(project()))
            implementation(gradleTestKit())
            implementation(testFixtures(projects.openapiModel))
        }

        gradlePlugin.testSourceSets(sources)

        targets.configureEach {
            testTask {
                environment("fixtureDir", project.file("src/testFixtures").path)
                javaLauncher.set(javaToolchains.launcherFor { languageVersion.set(JavaLanguageVersion.of(21)) })
            }
        }
    }
}
