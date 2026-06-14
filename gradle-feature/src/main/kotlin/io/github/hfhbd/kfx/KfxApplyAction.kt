package io.github.hfhbd.kfx

import org.gradle.api.file.SourceDirectorySet
import org.gradle.features.binding.ProjectFeatureApplicationContext

abstract class KfxApplyAction {
    fun ProjectFeatureApplicationContext.apply(
        definition: KfxDefinition,
        buildModel: KfxBuildModel,
        sourceDirectorySet: SourceDirectorySet,
    ) {
        buildModel.openApi.all {
            sourceDirectorySet.srcDir(it.outputDirectory)
        }

        for (openApi in definition.openApi) {
            val openApiBuildModel = getBuildModel(openApi)

            @Suppress("INVISIBLE_REFERENCE")
            openApi.dependencies.compiler.add("$GROUP:openapi-fir:$VERSION")
            @Suppress("INVISIBLE_REFERENCE")
            openApi.dependencies.compiler.add("$GROUP:ir-packagename:$VERSION")

            openApiBuildModel.classpath.configure {
                it.fromDependencyCollector(openApi.dependencies.compiler)
            }

            openApiBuildModel.files.from(openApi.files)
            openApiBuildModel.packageName.convention(openApi.packageName)

            buildModel.openApi.add(openApiBuildModel)
        }
    }
}
