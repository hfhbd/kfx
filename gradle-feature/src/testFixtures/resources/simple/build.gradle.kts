jvmApplication {
    kotlin {
        serialization {  }
    }

    dependencies {
        implementation(libs.ktor.client.core)
        implementation(libs.serialization.json)
    }

    kfx {
        openApi {
            openApi("grip") {
                files.from(file("grip.json"))

                dependencies {
                    compiler(ktorClient())
                    compiler(kotlinxJson())
                    compiler(contextualDate())
                    compiler(kotlinClasses())
                }
            }
        }
    }

    testing {
        dependencies {
            implementation("org.jetbrains.kotlin:kotlin-test")

            implementation(libs.ktor.server.test.host)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.server.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }

        kfx {
            openApi {
                openApi("gripServer") {
                    files.from(file("grip.json"))

                    dependencies {
                        compiler(ktorServer())
                    }
                }
            }
        }
    }
}
