pluginManagement {
    includeBuild("../../../../../")
    repositories {
        mavenCentral()
        gradlePluginPortal()
        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://raw.githubusercontent.com/Kotlin/declarative-gradle-jetbrains-ecosystem-plugin/refs/heads/maven2")
                }
            }
            filter {
                includeGroup("org.jetbrains.ecosystem")
            }
        }
    }
}

plugins {
    id("org.jetbrains.ecosystem").version("0.117.0")
    id("io.github.hfhbd.kfx.kotlin-features")
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        mavenCentral()
    }
    versionCatalogs.register("libs") {
        from(files("../../../../../gradle/libs.versions.toml"))
    }
}

rootProject.name = "simple"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

includeBuild("../../../../../")
