package io.github.hfhbd.kfx

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.features.annotations.BindsProjectFeature
import org.gradle.features.binding.ProjectFeatureApplicationContext
import org.gradle.features.binding.ProjectFeatureApplyAction
import org.gradle.features.binding.ProjectFeatureBinding
import org.gradle.features.binding.ProjectFeatureBindingBuilder
import org.gradle.features.dsl.bindProjectFeature
import org.jetbrains.kotlin.gradle.declarative.common.definitions.TestingExtension
import org.jetbrains.kotlin.gradle.declarative.projecttypes.jvmapplication.JvmApplicationProjectType

@BindsProjectFeature(KfxFeature.Binding::class)
class KfxFeature : Plugin<Project> {
    override fun apply(target: Project) = Unit

    class Binding : ProjectFeatureBinding {
        override fun bind(builder: ProjectFeatureBindingBuilder) {
            builder.bindProjectFeature("kfx", MainApplyAction::class)
                .withUnsafeDefinition()
            builder.bindProjectFeature("kfx", TestApplyAction::class)
                .withUnsafeDefinition()
        }

        internal abstract class MainApplyAction :
            KfxApplyAction(),
            ProjectFeatureApplyAction<KfxDefinition, KfxBuildModel, JvmApplicationProjectType> {
            override fun apply(
                context: ProjectFeatureApplicationContext,
                definition: KfxDefinition,
                buildModel: KfxBuildModel,
                targetDefinition: JvmApplicationProjectType,
            ) {
                val parentBuildModel = context.getBuildModel(targetDefinition)
                val compilationUnits = parentBuildModel.compilationUnits
                val sourceDirectorySet = compilationUnits.getByName("main").sources

                apply(definition, sourceDirectorySet)
            }
        }

        internal abstract class TestApplyAction :
            KfxApplyAction(),
            ProjectFeatureApplyAction<KfxDefinition, KfxBuildModel, TestingExtension> {
            override fun apply(
                context: ProjectFeatureApplicationContext,
                definition: KfxDefinition,
                buildModel: KfxBuildModel,
                targetDefinition: TestingExtension,
            ) {
                val parentBuildModel = context.getBuildModel(targetDefinition)
                val compilationUnits = parentBuildModel.compilationUnits
                val sourceDirectorySet = compilationUnits.getByName("main").sources

                apply(definition, sourceDirectorySet)
            }
        }
    }
}
