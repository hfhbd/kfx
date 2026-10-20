package io.github.hfhbd.kfx

import org.gradle.api.Named
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.artifacts.DependencyScopeConfiguration
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.features.binding.BuildModel
import org.gradle.features.binding.Definition

interface Wsdl : Kfx, Definition<WsdlBuildModel> {
    val wsdlFiles: ConfigurableFileCollection
    val schemaFiles: ConfigurableFileCollection
}

interface WsdlBuildModel : BuildModel, Named {
    val wsdlFiles: ConfigurableFileCollection
    val schemaFiles: ConfigurableFileCollection

    val compilerDependencies: NamedDomainObjectProvider<DependencyScopeConfiguration>

    val outputDirectory: DirectoryProperty
}

internal abstract class DefaultWsdlBuildModel : WsdlBuildModel {
    lateinit var internalName: String
    override fun getName(): String = internalName

    override lateinit var compilerDependencies: NamedDomainObjectProvider<DependencyScopeConfiguration>
}
