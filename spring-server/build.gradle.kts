plugins {
    id("compilerModule")
    kotlin("plugin.spring")
}

dependencies {
    implementation(projects.kotlinPoet)

    testFixturesApi(testFixtures(projects.openapiFir))
}

testing.suites {
    register("a", JvmTestSuite::class) {
        dependencies {
            implementation(projects.kotlin)
            implementation(projects.creatorKotlinxjson)
            implementation(testFixtures(project()))

            implementation(platform(libs.spring.bom))
            implementation("org.springframework.boot:spring-boot-starter-webflux")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
            implementation("org.springframework.boot:spring-boot-starter-test")
            implementation(libs.serialization.json)

            implementation(testFixtures(projects.openapiModel))
        }

        targets.configureEach {
            testTask {
                javaLauncher.set(javaToolchains.launcherFor {
                    languageVersion.set(JavaLanguageVersion.of(17))
                })
            }
        }
        sources {
            tasks.named(compileJavaTaskName, JavaCompile::class) {
                javaCompiler.set(javaToolchains.compilerFor {
                    languageVersion.set(JavaLanguageVersion.of(17))
                })
            }
            tasks.named("compile${name.replaceFirstChar { it.uppercaseChar() }}Kotlin", org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile::class) {
                compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
            }
        }
    }
}
