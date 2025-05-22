package io.github.hfhbd.kfx

import org.gradle.api.artifacts.dsl.Dependencies
import org.gradle.api.artifacts.dsl.DependencyCollector

interface KfxDependencies : Dependencies {
    val compiler: DependencyCollector

    fun kotlin() = dependencyFactory.create("$GROUP:kotlin:$VERSION")
    fun ktorClient() = dependencyFactory.create("$GROUP:ktor-client:$VERSION")
    fun ktorServer() = dependencyFactory.create("$GROUP:ktor-server:$VERSION")

    fun kotlinxJson() = dependencyFactory.create("$GROUP:creator-kotlinxjson:$VERSION")
    fun kotlinxXmlutil() = dependencyFactory.create("$GROUP:creator-xmlutil:$VERSION")

    fun gsb() = dependencyFactory.create("$GROUP:gsb:$VERSION")
    fun validation() = dependencyFactory.create("$GROUP:validation:$VERSION")
    fun removeType() = dependencyFactory.create("$GROUP:ir-removetype:$VERSION")
    fun odata() = dependencyFactory.create("$GROUP:ir-odata:$VERSION")
    fun contextualDate() = dependencyFactory.create("$GROUP:contextual-date:$VERSION")
}
