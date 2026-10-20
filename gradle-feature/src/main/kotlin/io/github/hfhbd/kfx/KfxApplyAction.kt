package io.github.hfhbd.kfx

import io.github.hfhbd.kfx.openapi.ConvertOpenApiFiles
import org.gradle.api.artifacts.dsl.DependencyFactory
import org.gradle.api.file.SourceDirectorySet
import org.gradle.features.binding.ProjectFeatureApplicationContext
import org.gradle.features.file.ProjectFeatureLayout
import org.gradle.features.registration.ConfigurationRegistrar
import org.gradle.features.registration.TaskRegistrar
import javax.inject.Inject

abstract class KfxApplyAction {
    @get:Inject abstract val configurations: ConfigurationRegistrar

    @get:Inject abstract val tasks: TaskRegistrar

    @get:Inject abstract val layout: ProjectFeatureLayout

    @get:Inject abstract val dependencyFactory: DependencyFactory

    fun ProjectFeatureApplicationContext.apply(
        definition: KfxDefinition,
        buildModel: KfxBuildModel,
        sourceDirectorySet: SourceDirectorySet,
    ) {
        buildModel.openApi.all { openApi ->
            @Suppress("INVISIBLE_REFERENCE")
            openApi.compilerDependencies.configure {
                it.dependencies.add(dependencyFactory.create("$GROUP:ir-packagename:$VERSION"))
                it.dependencies.add(dependencyFactory.create("$GROUP:openapi-fir:$VERSION"))
            }

            val classpath = configurations.resolvable("kfxOpenApiClasspath${openApi.name}") {
                it.extendsFrom(openApi.compilerDependencies)
            }

            openApi.outputDirectory.convention(layout.contextBuildDirectory.map { it.dir("generated/kfx/openapi/${openApi.name}/") })

            val task = tasks.register("convertOpenApiFiles${openApi.name}", ConvertOpenApiFiles::class.java) {
                @Suppress("INVISIBLE_REFERENCE")
                it.classpath.from(classpath)
                it.openapiFiles.from(openApi.files)
                it.outputDirectory.convention(openApi.outputDirectory)
                it.packageName.set(openApi.packageName)
            }
            sourceDirectorySet.srcDir(task)
        }

        for (openApi in definition.openApi) {
            val openApiBuildModel = getBuildModel(openApi)
            openApiBuildModel as DefaultOpenApiBuildModel
            // Move to constructor after https://github.com/gradle/gradle/issues/38207
            openApiBuildModel.internalName = openApi.name
            openApiBuildModel.compilerDependencies = configurations.dependencyScope("kfxOpenApi${openApi.name}")

            openApiBuildModel.compilerDependencies.configure {
                it.fromDependencyCollector(openApi.dependencies.compiler)
            }
            openApiBuildModel.files.from(openApi.files)
            openApiBuildModel.packageName.convention(openApi.packageName)

            buildModel.openApi.add(openApiBuildModel)
        }
    }
}
