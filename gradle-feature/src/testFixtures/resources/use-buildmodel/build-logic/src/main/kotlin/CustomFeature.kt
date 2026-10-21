import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.features.annotations.BindsProjectFeature
import org.gradle.features.binding.ProjectFeatureApplicationContext
import org.gradle.features.binding.ProjectFeatureApplyAction
import org.gradle.features.binding.ProjectFeatureBinding
import org.gradle.features.binding.ProjectFeatureBindingBuilder
import org.gradle.features.dsl.bindProjectFeature
import javax.inject.Inject
import org.gradle.features.binding.BuildModel
import org.gradle.features.binding.Definition
import io.github.hfhbd.kfx.KfxDefinition
import org.gradle.api.model.ObjectFactory
import org.gradle.features.file.ProjectFeatureLayout
import org.gradle.features.registration.ConfigurationRegistrar
import org.gradle.kotlin.dsl.newInstance

@BindsProjectFeature(CustomFeature.Binding::class)
class CustomFeature : Plugin<Project> {
    override fun apply(target: Project) = Unit

    class Binding : ProjectFeatureBinding {
        override fun bind(builder: ProjectFeatureBindingBuilder) {
            builder.bindProjectFeature("custom", ApplyAction::class)
        }

        internal abstract class ApplyAction :
            ProjectFeatureApplyAction<CustomDefinition, BuildModel.None, KfxDefinition> {

            @get:Inject abstract val layout: ProjectFeatureLayout
            @get:Inject abstract val objectFactory: ObjectFactory
            @get:Inject abstract val configurations: ConfigurationRegistrar

            override fun apply(
                context: ProjectFeatureApplicationContext,
                definition: CustomDefinition,
                buildModel: BuildModel.None,
                targetDefinition: KfxDefinition,
            ) {
                val parentBuildModel = context.getBuildModel(targetDefinition)

                // use register after https://github.com/gradle/gradle/issues/38207
                @Suppress("INVISIBLE_REFERENCE")
                parentBuildModel.openApi.add(
                    objectFactory.newInstance<io.github.hfhbd.kfx.DefaultOpenApiBuildModel>().apply {
                        internalName = "grip"
                        compilerDependencies = configurations.dependencyScope("kfxOpenApi${name}")

                        files.from(layout.projectDirectory.file("grip.json"))

                        @Suppress("INVISIBLE_REFERENCE")
                        compilerDependencies.configure {
                            dependencies.add(parentBuildModel.moduleDependencies.ktorClient())
                            dependencies.add(parentBuildModel.moduleDependencies.kotlinxJson())
                            dependencies.add(parentBuildModel.moduleDependencies.contextualDate())
                            dependencies.add(parentBuildModel.moduleDependencies.kotlinClasses())
                        }
                    }
                )
            }
        }
    }
}

interface CustomDefinition : Definition<BuildModel.None>
