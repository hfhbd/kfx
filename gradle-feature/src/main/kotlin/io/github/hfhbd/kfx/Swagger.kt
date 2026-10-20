package io.github.hfhbd.kfx

import org.gradle.api.Named
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.artifacts.DependencyScopeConfiguration
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.features.binding.BuildModel
import org.gradle.features.binding.Definition

interface Swagger :
    Kfx,
    Definition<SwaggerBuildModel> {
    val files: ConfigurableFileCollection

    val packageName: Property<String>
}

interface SwaggerBuildModel :
    BuildModel,
    Named {
    val files: ConfigurableFileCollection
    val packageName: Property<String>
    val compilerDependencies: NamedDomainObjectProvider<DependencyScopeConfiguration>
    val outputDirectory: DirectoryProperty
}

internal abstract class DefaultSwaggerBuildModel : SwaggerBuildModel {
    lateinit var internalName: String
    override fun getName(): String = internalName

    override lateinit var compilerDependencies: NamedDomainObjectProvider<DependencyScopeConfiguration>
}
