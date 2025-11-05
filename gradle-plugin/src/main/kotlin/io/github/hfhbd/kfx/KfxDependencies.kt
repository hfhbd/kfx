package io.github.hfhbd.kfx

import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.Dependencies
import org.gradle.api.artifacts.dsl.DependencyCollector

interface KfxDependencies : Dependencies {
    val compiler: DependencyCollector

    fun kotlin(): ExternalModuleDependency = dependencyFactory.create("$GROUP:kotlin:$VERSION")
    fun ktorClient(): ExternalModuleDependency = dependencyFactory.create("$GROUP:ktor-client:$VERSION")
    fun ktorServer(): ExternalModuleDependency = dependencyFactory.create("$GROUP:ktor-server:$VERSION")

    fun springServer(): ExternalModuleDependency = dependencyFactory.create("$GROUP:spring-server:$VERSION")

    fun kotlinxJson(): ExternalModuleDependency = dependencyFactory.create("$GROUP:creator-kotlinxjson:$VERSION")
    fun kotlinxXmlutil(): ExternalModuleDependency = dependencyFactory.create("$GROUP:creator-xmlutil:$VERSION")

    fun soap11(): ExternalModuleDependency = dependencyFactory.create("$GROUP:soap11:$VERSION")
    fun validation(): ExternalModuleDependency = dependencyFactory.create("$GROUP:validation:$VERSION")
    fun removeType(): ExternalModuleDependency = dependencyFactory.create("$GROUP:ir-removetype:$VERSION")
    fun odata(): ExternalModuleDependency = dependencyFactory.create("$GROUP:ir-odata:$VERSION")
    fun contextualDate(): ExternalModuleDependency = dependencyFactory.create("$GROUP:contextual-date:$VERSION")
}
