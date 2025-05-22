package io.github.hfhbd.kfx

import org.gradle.api.Action
import org.gradle.api.Named
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.tasks.Nested

interface Kfx : Named {
    @get:Nested
    val dependencies: KfxDependencies

    fun dependencies(action: Action<KfxDependencies>) {
        action.execute(dependencies)
    }

    fun usingSourceSet(sourceSet: SourceDirectorySet)
}
