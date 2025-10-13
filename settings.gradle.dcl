pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("mySettings")
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

include(":spring-server")

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

include(":xsd-model")
include(":xsd-fir")
include(":wsdl-model")
include(":wsdl-fir")

include(":soap")
include(":soap-runtime")

include(":ktor-server-soap-plugin")
