import org.gradle.api.Action
import org.gradle.api.tasks.Nested

interface MergeDetektExtension {
    @get:Nested
    val dependencies: SarifDependencies

    fun dependencies(configure: Action<SarifDependencies>) {
        configure.execute(dependencies)
    }
}
