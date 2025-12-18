package io.github.hfhbd.kfx

import org.gradle.api.Action
import org.gradle.api.Named
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.tasks.Nested
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

interface Kfx : Named {
    override fun getName(): String

    @get:Nested
    val dependencies: KfxDependencies

    fun dependencies(action: Action<KfxDependencies>) {
        action.execute(dependencies)
    }

    fun usingKotlinSourceSet(kotlinSourceSet: KotlinSourceSet)

    fun usingKotlinSourceSet(kotlinSourceSet: NamedDomainObjectProvider<KotlinSourceSet>) {
        kotlinSourceSet.configure {
            usingKotlinSourceSet(it)
        }
    }
}
