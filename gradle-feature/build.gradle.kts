import org.jetbrains.kotlin.gradle.tasks.*

plugins {
    id("java-gradle-plugin")
    kotlin("jvm")
    id("setup")
    id("java-test-fixtures")
}

kotlin.jvmToolchain(8)

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    implementation(projects.gradlePlugin)
}

tasks.validatePlugins {
    enableStricterValidation.set(true)
}

configurations.configureEach {
   if (isCanBeConsumed) {
       attributes {
           attribute(GradlePluginApiVersion.GRADLE_PLUGIN_API_VERSION_ATTRIBUTE, named("9.5.0"))
       }
   }
}

// Workaround for clash between `signature` and `archives`; remove when bumping to Gradle 10:
configurations.archives {
    attributes {
        attribute(Attribute.of("deprecated", String::class.java), "true")
    }
}

testing.suites {
    withType(JvmTestSuite::class).configureEach {
        useKotlinTest()
    }

    register("integrationTest", JvmTestSuite::class) {
        dependencies {
            implementation(testFixtures(project()))
            implementation(gradleTestKit())
        }

        gradlePlugin.testSourceSets(sources)

        targets.configureEach {
            testTask {
                javaLauncher.set(javaToolchains.launcherFor { languageVersion.set(JavaLanguageVersion.of(21)) })
            }
        }
    }
}
