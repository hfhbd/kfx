jvmApplication {
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
}
