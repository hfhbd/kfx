package io.github.hfhbd.kfx.openapi

import io.github.hfhbd.kfx.GROUP
import io.github.hfhbd.kfx.Kfx
import io.github.hfhbd.kfx.KfxDependencies
import io.github.hfhbd.kfx.VERSION
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.register
import javax.inject.Inject

abstract class OpenApi : Kfx {
    abstract val files: ConfigurableFileCollection

    abstract val packageName: Property<String>

    @get:Nested
    abstract override val dependencies: KfxDependencies

    @get:Inject
    internal abstract val configurations: ConfigurationContainer

    @get:Inject
    internal abstract val tasks: TaskContainer

    override fun usingSourceSet(sourceSet: SourceDirectorySet) {
        val serviceName: String = name

        dependencies.compiler.add("$GROUP:openapi-fir:$VERSION")
        dependencies.compiler.add("$GROUP:ir-packagename:$VERSION")

        val kfxOpenApi = configurations.dependencyScope("kfxOpenApi$serviceName") {
            fromDependencyCollector(this@OpenApi.dependencies.compiler)
        }
        val kfxOpenApiClasspath = configurations.resolvable("kfxOpenApiClasspath$serviceName") {
            extendsFrom(kfxOpenApi.get())
        }
        sourceSet.srcDir(
            tasks.register<ConvertOpenApiFiles>("convertOpenApiFiles$serviceName") {
                classpath.from(kfxOpenApiClasspath)
                openapiFiles.from(this@OpenApi.files)
                outputFolder.convention(project.layout.buildDirectory.dir("generated/kfx/openapi/$serviceName/"))
                packageName.set(this@OpenApi.packageName)
            },
        )
    }
}
