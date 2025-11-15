import org.gradle.api.artifacts.dsl.Dependencies
import org.gradle.api.artifacts.dsl.DependencyCollector

interface SarifDependencies : Dependencies {
    val sarif: DependencyCollector
}
