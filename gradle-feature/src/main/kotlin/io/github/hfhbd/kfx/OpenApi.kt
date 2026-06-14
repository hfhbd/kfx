package io.github.hfhbd.kfx

import io.github.hfhbd.kfx.openapi.ConvertOpenApiFiles
import org.gradle.api.Named
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.artifacts.ResolvableConfiguration
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.features.binding.BuildModel
import org.gradle.features.binding.Definition
import org.gradle.features.file.ProjectFeatureLayout
import org.gradle.features.registration.ConfigurationRegistrar
import org.gradle.features.registration.TaskRegistrar
import javax.inject.Inject

interface OpenApi : Kfx, Definition<OpenApiBuildModel> {
    val files: ConfigurableFileCollection

    val packageName: Property<String>
}

interface OpenApiBuildModel : BuildModel, Named {
    val files: ConfigurableFileCollection
    val packageName: Property<String>

    val classpath: NamedDomainObjectProvider<ResolvableConfiguration>

    val outputDirectory: DirectoryProperty
}

internal abstract class DefaultOpenApiBuildModel @Inject constructor(
    configurations: ConfigurationRegistrar,
    tasks: TaskRegistrar,
    layout: ProjectFeatureLayout,
) : OpenApiBuildModel {

    override val classpath: NamedDomainObjectProvider<ResolvableConfiguration> = configurations.resolvable(
        "kfxOpenApiClasspath$name",
    )

    init {
        outputDirectory.convention(layout.contextBuildDirectory.map { it.dir("generated/kfx/openapi/$name/") })
        tasks.register("convertOpenApiFiles$name", ConvertOpenApiFiles::class.java) {
            @Suppress("INVISIBLE_REFERENCE")
            it.classpath.from(classpath)
            it.openapiFiles.from(files)
            it.outputDirectory.convention(outputDirectory)
            it.packageName.set(packageName)
        }
    }
}
