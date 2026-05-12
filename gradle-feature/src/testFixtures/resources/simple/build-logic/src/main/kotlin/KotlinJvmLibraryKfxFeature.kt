import io.github.hfhbd.kfx.KfxApplyAction
import io.github.hfhbd.kfx.KfxBuildModel
import io.github.hfhbd.kfx.KfxDefinition
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.features.annotations.BindsProjectFeature
import org.gradle.features.binding.ProjectFeatureApplicationContext
import org.gradle.features.binding.ProjectFeatureApplyAction
import org.gradle.features.binding.ProjectFeatureBinding
import org.gradle.features.binding.ProjectFeatureBindingBuilder
import org.gradle.features.dsl.bindProjectFeature

@BindsProjectFeature(KotlinJvmLibraryKfxFeature.Binding::class)
class KotlinJvmLibraryKfxFeature : Plugin<Project> {
    override fun apply(target: Project) {}

    class Binding : ProjectFeatureBinding {
        override fun bind(builder: ProjectFeatureBindingBuilder) {
            builder.bindProjectFeature("kfx", ApplyAction::class)
        }

        internal abstract class ApplyAction : KfxApplyAction(), ProjectFeatureApplyAction<KfxDefinition, KfxBuildModel, KotlinJvmLibraryDefinition> {
            override fun apply(
                context: ProjectFeatureApplicationContext,
                definition: KfxDefinition,
                buildModel: KfxBuildModel,
                targetDefinition: KotlinJvmLibraryDefinition,
            ) {
                val kotlinBuildModel = context.getBuildModel(targetDefinition)
                apply(definition, kotlinBuildModel.sourceSets.getByName("main").kotlin)
            }
        }
    }
}
