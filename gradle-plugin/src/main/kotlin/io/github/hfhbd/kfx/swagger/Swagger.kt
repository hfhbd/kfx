package io.github.hfhbd.kfx.swagger

import io.github.hfhbd.kfx.Kfx
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import javax.inject.Inject

abstract class Swagger : Kfx {
    abstract val files: ConfigurableFileCollection

    abstract val packageName: Property<String>

    @get:Inject
    internal abstract val configurations: ConfigurationContainer

    @get:Inject
    internal abstract val tasks: TaskContainer

    override fun usingKotlinSourceSet(kotlinSourceSet: KotlinSourceSet) {
        val serviceName = name

        dependencies.compiler.add(dependencies.kfxModule("swagger-fir"))
        dependencies.compiler.add(dependencies.kfxModule("ir-packagename"))

        val kfxSwaggerClasspath = configurations.resolvable("kfxSwaggerClasspath$serviceName") {
            it.fromDependencyCollector(this@Swagger.dependencies.compiler)
        }

        kotlinSourceSet.kotlin.srcDir(
            tasks.register("convertSwaggerFiles$serviceName", ConvertSwaggerFiles::class.java) {
                it.classpath.from(kfxSwaggerClasspath)
                it.swaggerFiles.from(files)
                it.outputDirectory.convention(
                    it.project.layout.buildDirectory.dir("generated/kfx/swagger/$serviceName"),
                )
                it.packageName.convention(packageName)
            },
        )
    }
}
