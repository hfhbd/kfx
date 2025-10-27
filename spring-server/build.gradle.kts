plugins {
    id("compilerModule")
    kotlin("plugin.spring")
}

dependencies {
    implementation(projects.kotlinPoet)

    testImplementation(platform(libs.spring.bom))
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(libs.serialization.json)
}

tasks.compileTestJava {
    javaCompiler.set(javaToolchains.compilerFor {
        languageVersion.set(JavaLanguageVersion.of(17))
    })
}

tasks.compileTestKotlin {
    compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
}

testing.suites.named("test", JvmTestSuite::class) {
    dependencies {
        implementation(testFixtures(projects.openapiFir))

        implementation(projects.kotlin)
        implementation(projects.creatorKotlinxjson)
        implementation(projects.springServer)

        implementation(testFixtures(projects.openapiModel))
    }

    targets.configureEach {
        testTask {
            outputs.dir("build/kfx-tests/test")

            javaLauncher.set(javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(17))
            })
        }
    }
}
