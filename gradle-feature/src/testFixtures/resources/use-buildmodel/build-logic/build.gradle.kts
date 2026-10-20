plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("io.github.hfhbd.kfx:gradle-feature")
}

gradlePlugin.plugins.register("custom-plugin") {
    implementationClass = "CustomPlugin"
}
