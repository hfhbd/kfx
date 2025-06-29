import io.github.hfhbd.kfx.openapi.OpenApi

plugins {
    id("io.github.hfhbd.kfx")
}

kfx.register("grip", OpenApi::class) {
    files.from(file("grip.json"))
    dependencies {
        compiler(ktorClient())
        compiler(kotlinxJson())
        compiler(contextualDate())
        compiler(kotlin())
    }
}
