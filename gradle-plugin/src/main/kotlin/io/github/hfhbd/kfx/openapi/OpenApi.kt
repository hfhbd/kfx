package io.github.hfhbd.kfx.openapi

import io.github.hfhbd.kfx.Kfx
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import javax.inject.Inject

abstract class OpenApi : Kfx {
    abstract val files: ConfigurableFileCollection

    abstract val packageName: Property<String>

    @get:Inject
    internal abstract val configurations: ConfigurationContainer

    @get:Inject
    internal abstract val tasks: TaskContainer

    override fun usingKotlinSourceSet(kotlinSourceSet: KotlinSourceSet) {
        val serviceName = name

        dependencies.compiler.add(dependencies.kfxModule("openapi-fir"))
        dependencies.compiler.add(dependencies.kfxModule("ir-packagename"))

        val kfxOpenApiClasspath = configurations.resolvable("kfxOpenApiClasspath$serviceName") {
            it.fromDependencyCollector(this@OpenApi.dependencies.compiler)
        }

        kotlinSourceSet.kotlin.srcDir(
            tasks.register("convertOpenApiFiles$serviceName", ConvertOpenApiFiles::class.java) {
                it.classpath.from(kfxOpenApiClasspath)
                it.openapiFiles.from(files)
                it.outputDirectory.convention(
                    it.project.layout.buildDirectory.dir("generated/kfx/openapi/$serviceName/"),
                )
                it.packageName.set(packageName)
            },
        )
    }
}
