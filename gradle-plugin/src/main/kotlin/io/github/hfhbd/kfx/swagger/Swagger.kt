package io.github.hfhbd.kfx.swagger

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

abstract class Swagger : Kfx {
    abstract val files: ConfigurableFileCollection

    @get:Nested
    abstract override val dependencies: KfxDependencies

    abstract val packageName: Property<String>

    @get:Inject
    internal abstract val configurations: ConfigurationContainer

    @get:Inject
    internal abstract val tasks: TaskContainer

    override fun usingSourceSet(sourceSet: SourceDirectorySet) {
        val serviceName: String = name

        dependencies.compiler.add("$GROUP:swagger-fir:$VERSION")
        dependencies.compiler.add("$GROUP:ir-packagename:$VERSION")

        val kfxSwagger = configurations.dependencyScope("kfxSwagger$serviceName") {
            fromDependencyCollector(this@Swagger.dependencies.compiler)
        }
        val kfxSwaggerClasspath = configurations.resolvable("kfxSwaggerClasspath$serviceName") {
            extendsFrom(kfxSwagger.get())
        }
        sourceSet.srcDir(
            tasks.register("convertSwaggerFiles$serviceName", ConvertSwaggerFiles::class) {
                classpath.from(kfxSwaggerClasspath)
                swaggerFiles.from(this@Swagger.files)
                outputFolder.convention(project.layout.buildDirectory.dir("generated/kfx/swagger/$serviceName"))
                packageName.convention(this@Swagger.packageName)
            },
        )
    }
}
