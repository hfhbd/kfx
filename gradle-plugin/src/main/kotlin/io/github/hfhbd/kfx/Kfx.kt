package io.github.hfhbd.kfx

import org.gradle.api.Action
import org.gradle.api.Named
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.SourceSet

interface Kfx : Named {
    @get:Nested
    val dependencies: KfxDependencies

    fun dependencies(action: Action<KfxDependencies>) {
        action.execute(dependencies)
    }

    fun usingKotlinSourceSet(sourceSet: SourceSet)

    fun usingKotlinSourceSet(sourceSet: NamedDomainObjectProvider<SourceSet>) {
        sourceSet.configure {
            usingKotlinSourceSet(it)
        }
    }

    fun usingKotlinSourceSet(sourceSetName: String)
}
