plugins {
    id("jvmModule")
}

dependencies {
    api(libs.ktor.server.core)
    api(libs.serialization.xml)

    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.ktor.server.content.negotiation)
    testImplementation(libs.ktor.client.content.negotiation)
    testImplementation(libs.ktor.serialization.kotlinx.xml)
    testImplementation(libs.ktor.server.double.receive)

    testImplementation(testFixtures(projects.wsdlFir))
}

licensee {
    allow("MIT")
}

testing.suites.named("test", JvmTestSuite::class) {
    targets.configureEach {
        testTask {
            javaLauncher.set(javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(11))
            })
        }
    }

    dependencies {
        implementation(testFixtures(projects.core))
        implementation(libs.serialization.json)
        implementation(projects.wsdlModel)
        implementation(libs.ktor.client.cio)
        implementation(libs.ktor.client.logging)
        implementation(libs.logback)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.ktor.serialization.kotlinx.xml)
    }
}
