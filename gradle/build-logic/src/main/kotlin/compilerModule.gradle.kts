plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("setup")
    id("java-test-fixtures")
    id("jvm-test-suite")
    id("app.softwork.serviceloader-compiler")
    id("app.softwork.validation")
}

kotlin {
    jvmToolchain(8)

    compilerOptions {
        freeCompilerArgs.add("-Xreturn-value-checker=full")
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

testing.suites.withType(JvmTestSuite::class).configureEach {
    useKotlinTest()
    targets.configureEach {
        val target = this
        tasks.check {
            dependsOn(testTask)
        }
        testTask {
            outputs.dir("build/kfx-tests/${target.name}")
        }
    }
}

publishing {
    publications.register<MavenPublication>("gpr") {
        from(components["java"])
    }
}
