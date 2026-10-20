import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.features.annotations.RegistersProjectFeatures

@RegistersProjectFeatures(
    CustomFeature::class,
)
abstract class CustomPlugin : Plugin<Settings> {
    override fun apply(target: Settings) = Unit
}
