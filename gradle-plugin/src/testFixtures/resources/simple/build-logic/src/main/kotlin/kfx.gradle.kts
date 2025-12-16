import io.github.hfhbd.kfx.openapi.OpenApi

plugins {
    id("io.github.hfhbd.kfx")
}

kfx.register("grip", OpenApi::class) {
    dependencies {
        compiler(ktorClient())
        compiler(kotlinxJson())
        compiler(contextualDate())
        compiler(kotlinClasses())
    }
}
