package io.github.hfhbd.kfx

import org.gradle.api.artifacts.dsl.Dependencies
import org.gradle.api.artifacts.dsl.DependencyCollector

interface KfxDependencies : KfxModuleDependencies {
    val compiler: DependencyCollector
}

interface KfxModuleDependencies : Dependencies {
    fun kfxModule(module: String) = module("$GROUP:$module:$VERSION")

    fun kotlinClasses() = kfxModule("kotlin")
    fun ktorClient() = kfxModule("ktor-client")
    fun ktorServer() = kfxModule("ktor-server")
    fun springServer() = kfxModule("spring-server")
    fun kotlinxJson() = kfxModule("creator-kotlinxjson")
    fun kotlinxXmlutil() = kfxModule("creator-xmlutil")
    fun soap11() = kfxModule("soap11")
    fun validation() = kfxModule("validation")
    fun responseClasses() = kfxModule("response-classes")
    fun odata() = kfxModule("ir-odata")
    fun contextualDate() = kfxModule("contextual-date")
}
