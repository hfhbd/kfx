import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyConstraintFactory
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.newInstance
import javax.inject.Inject

interface A {
    @get:Inject
    val dependencyConstraintFactory: DependencyConstraintFactory
}

fun ObjectFactory.getDependencyConstraintFactory() = newInstance(A::class).dependencyConstraintFactory

fun Configuration.addConstraints(dependencyConstraintFactory: DependencyConstraintFactory) {
    dependencyConstraints.addAllLater(
        dependencies.elements.map {
            it.mapNotNull {
                when (it) {
                    is MinimalExternalModuleDependency -> dependencyConstraintFactory.create(it)
                    is ProjectDependency -> dependencyConstraintFactory.create(it)
                    else -> null
                }
            }
        }
    )
}
