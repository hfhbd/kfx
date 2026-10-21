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
    api(libs.kotlin.gradle.plugin.api)

    compileOnly(projects.wsdlFir)
    compileOnly(projects.swaggerFir)
    compileOnly(projects.irPackagename)
    compileOnly(projects.openapiFir)
}

tasks.validatePlugins {
    enableStricterValidation = true
}

configurations.configureEach {
   if (isCanBeConsumed) {
       attributes {
           attribute(GradlePluginApiVersion.GRADLE_PLUGIN_API_VERSION_ATTRIBUTE, objects.named("8.11"))
       }
   }
}

// Workaround for clash between `signature` and `archives`; remove when bumping to Gradle 10:
configurations.archives {
    attributes {
        attribute(Attribute.of("deprecated", String::class.java), "true")
    }
}

val storeVersion = tasks.register("storeVersion", StoreVersion::class)
sourceSets.main {
    kotlin.srcDir(storeVersion)
}

testing.suites {
    withType(JvmTestSuite::class).configureEach {
        useKotlinTest()
    }
}
