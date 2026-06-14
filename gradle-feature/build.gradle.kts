plugins {
    id("java-gradle-plugin")
    kotlin("jvm")
    id("setup")
    id("java-test-fixtures")
}

kotlin.jvmToolchain(17)

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    implementation(projects.gradlePlugin)
    compileOnly(libs.kotlin.ecosystem)
}

gradlePlugin.plugins.register("io.github.hfhbd.kfx-kotlin-features") {
    implementationClass = "io.github.hfhbd.kfx.KfxFeaturesSettingsPlugin"
    displayName = "kfx Declarative Kotlin Features"
    description = "kfx Declarative Kotlin Features"
}

tasks.validatePlugins {
    enableStricterValidation.set(true)
}

configurations.configureEach {
   if (isCanBeConsumed) {
       attributes {
           attribute(GradlePluginApiVersion.GRADLE_PLUGIN_API_VERSION_ATTRIBUTE, named("9.6.0"))
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
