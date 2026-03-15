import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import org.gradle.api.plugins.PluginManager
import org.gradle.api.artifacts.dsl.Dependencies
import org.gradle.api.artifacts.dsl.DependencyCollector
import org.gradle.features.annotations.BindsProjectType
import org.gradle.features.binding.BuildModel
import org.gradle.features.binding.Definition
import org.gradle.features.binding.ProjectFeatureApplicationContext
import org.gradle.features.binding.ProjectTypeBinding
import org.gradle.features.binding.ProjectTypeApplyAction
import org.gradle.features.binding.ProjectTypeBindingBuilder
import org.gradle.features.dsl.bindProjectType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension
import javax.inject.Inject
import org.gradle.features.annotations.RegistersProjectFeatures

@RegistersProjectFeatures(
    KotlinJvmLibraryProjectTypePlugin::class,
    KotlinJvmLibraryKfxFeature::class,
)
abstract class EcosystemPlugin : Plugin<Settings> {
    override fun apply(target: Settings) {}
}

@BindsProjectType(KotlinJvmLibraryProjectTypePlugin.Binding::class)
class KotlinJvmLibraryProjectTypePlugin : Plugin<Project> {
    override fun apply(target: Project) { }

    class Binding : ProjectTypeBinding {
        override fun bind(builder: ProjectTypeBindingBuilder) {
            builder.bindProjectType("kotlinJvmLibrary", ApplyAction::class)
                .withUnsafeApplyAction()
                .withUnsafeDefinition()
        }

        internal abstract class ApplyAction : ProjectTypeApplyAction<KotlinJvmLibraryDefinition, KotlinJvmLibraryBuildModel> {
            @get:Inject
            abstract val pluginManager: PluginManager

            @get:Inject
            abstract val project: Project

            override fun apply(
                context: ProjectFeatureApplicationContext,
                definition: KotlinJvmLibraryDefinition,
                buildModel: KotlinJvmLibraryBuildModel,
            ) {
                pluginManager.apply("org.jetbrains.kotlin.jvm")
                val extension = project.extensions.getByName("kotlin") as KotlinJvmExtension
                val mainSourceSet = extension.sourceSets.getByName("main")
                project.configurations.named(mainSourceSet.implementationConfigurationName) {
                    fromDependencyCollector(definition.dependencies.implementation)
                }
                buildModel.sourceSets.add(mainSourceSet)
            }
        }
    }
}

interface KotlinJvmLibraryDefinition : Definition<KotlinJvmLibraryBuildModel> {
    @get:org.gradle.api.tasks.Nested
    val dependencies: KotlinJvmLibraryDependencies
}
interface KotlinJvmLibraryDependencies : Dependencies {
    val implementation: DependencyCollector
}

interface KotlinJvmLibraryBuildModel : BuildModel {
    val sourceSets: NamedDomainObjectContainer<org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet>
}
