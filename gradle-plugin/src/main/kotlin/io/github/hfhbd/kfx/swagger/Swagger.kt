package io.github.hfhbd.kfx.swagger

import io.github.hfhbd.kfx.GROUP
import io.github.hfhbd.kfx.Kfx
import io.github.hfhbd.kfx.VERSION
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskContainer
import javax.inject.Inject

abstract class Swagger : Kfx {
    abstract val files: ConfigurableFileCollection

    abstract val packageName: Property<String>

    @get:Inject
    internal abstract val configurations: ConfigurationContainer

    @get:Inject
    internal abstract val tasks: TaskContainer

    @get:Inject
    internal abstract val sourceSets: SourceSetContainer

    override fun usingKotlinSourceSet(sourceSetName: String) {
        sourceSets.named(sourceSetName).configure {
            usingKotlinSourceSet(it)
        }
    }

    override fun usingKotlinSourceSet(sourceSet: SourceSet) {
        val serviceName: String = name

        dependencies.compiler.add("$GROUP:swagger-fir:$VERSION")
        dependencies.compiler.add("$GROUP:ir-packagename:$VERSION")

        val kfxSwaggerClasspath = configurations.resolvable("kfxSwaggerClasspath$serviceName") {
            it.fromDependencyCollector(this@Swagger.dependencies.compiler)
        }
        val kotlinSourceDirectorySet = (sourceSet as ExtensionAware).extensions.getByName(
            "kotlin",
        ) as SourceDirectorySet
        kotlinSourceDirectorySet.srcDir(
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
