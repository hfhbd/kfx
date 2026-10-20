package io.github.hfhbd.kfx

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.features.annotations.BindsProjectFeature
import org.gradle.features.binding.ProjectFeatureApplicationContext
import org.gradle.features.binding.ProjectFeatureApplyAction
import org.gradle.features.binding.ProjectFeatureBinding
import org.gradle.features.binding.ProjectFeatureBindingBuilder
import org.gradle.features.dsl.bindProjectFeature
import org.jetbrains.kotlin.gradle.declarative.projecttypes.jvmapplication.JvmApplicationProjectType
import org.jetbrains.kotlin.gradle.declarative.projecttypes.jvmapplication.JvmApplicationTestingExtension
import javax.inject.Inject

@BindsProjectFeature(KfxFeature.Binding::class)
class KfxFeature : Plugin<Project> {
    override fun apply(target: Project) = Unit

    class Binding : ProjectFeatureBinding {
        override fun bind(builder: ProjectFeatureBindingBuilder) {
            builder.bindProjectFeature("kfx", JvmMainApplyAction::class)
                .withUnsafeDefinition()
                .withNestedBuildModelImplementationType(
                    OpenApiBuildModel::class.java,
                    DefaultOpenApiBuildModel::class.java,
                )
                .withNestedBuildModelImplementationType(
                    SwaggerBuildModel::class.java,
                    DefaultSwaggerBuildModel::class.java,
                )
                .withNestedBuildModelImplementationType(WsdlBuildModel::class.java, DefaultWsdlBuildModel::class.java)
                .withNestedBuildModelImplementationType(XsdBuildModel::class.java, DefaultXsdBuildModel::class.java)

            builder.bindProjectFeature("kfx", JvmTestApplyAction::class)
                .withUnsafeDefinition()
                .withUnsafeApplyAction()
                .withNestedBuildModelImplementationType(
                    OpenApiBuildModel::class.java,
                    DefaultOpenApiBuildModel::class.java,
                )
                .withNestedBuildModelImplementationType(
                    SwaggerBuildModel::class.java,
                    DefaultSwaggerBuildModel::class.java,
                )
                .withNestedBuildModelImplementationType(WsdlBuildModel::class.java, DefaultWsdlBuildModel::class.java)
                .withNestedBuildModelImplementationType(XsdBuildModel::class.java, DefaultXsdBuildModel::class.java)
        }

        internal abstract class JvmMainApplyAction :
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

                context.apply(definition, buildModel, sourceDirectorySet)
            }
        }

        internal abstract class JvmTestApplyAction :
            KfxApplyAction(),
            ProjectFeatureApplyAction<KfxDefinition, KfxBuildModel, JvmApplicationTestingExtension> {
            @get:Inject
            abstract val sourceSets: SourceSetContainer

            override fun apply(
                context: ProjectFeatureApplicationContext,
                definition: KfxDefinition,
                buildModel: KfxBuildModel,
                targetDefinition: JvmApplicationTestingExtension,
            ) {
                val testSourceSet = sourceSets.getByName("test")
                val sourceDirectorySet = testSourceSet.extensions.getByName("kotlin") as SourceDirectorySet

                context.apply(definition, buildModel, sourceDirectorySet)
            }
        }
    }
}
