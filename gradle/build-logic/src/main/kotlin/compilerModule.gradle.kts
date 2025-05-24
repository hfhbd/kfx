plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("setup")
    id("publish")
    id("java-test-fixtures")
    id("jvm-test-suite")
    id("app.softwork.serviceloader-compiler")
    id("app.softwork.validation")
}

kotlin {
    jvmToolchain(8)

    compilerOptions {
        optIn.add("kotlin.uuid.ExperimentalUuidApi")
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

testing.suites.withType(JvmTestSuite::class).configureEach {
    useKotlinTest()
}

publishing {
    publications.register<MavenPublication>("gpr") {
        from(components["java"])
    }
}
