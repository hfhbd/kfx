pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("myRepos")
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    id("com.gradle.develocity") version "4.0.1"
}

develocity {
    buildScan {
        termsOfUseUrl.set("https://gradle.com/terms-of-service")
        termsOfUseAgree.set("yes")
        val isCI = providers.environmentVariable("CI").isPresent
        publishing {
            onlyIf { isCI }
        }
        tag("CI")
    }
}

rootProject.name = "kfx"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

include(":core")

include(":creator-xmlutil")
include(":kotlin")
include(":kotlin-poet")

include(":ktor-shared")
include(":ktor-client")
include(":ktor-server")

include(":ktor-server-runtime")

include(":validation")
include(":contextual-date")

include(":ir-packagename")
include(":ir-removetype")
include(":ir-odata")

include(":oauth2-runtime")

include(":gradle-plugin")

include(":openapi-model")
include(":openapi-fir")

include(":swagger-model")
include(":swagger-fir")
include(":creator-kotlinxcore")
include(":creator-kotlinxjson")

include(":wsdl-model")
include(":wsdl-fir")

include(":soap")
include(":soap-runtime")

include(":ktor-server-soap-plugin")
