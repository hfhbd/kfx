package io.github.hfhbd.kfx.xsd

import io.github.hfhbd.kfx.GROUP
import io.github.hfhbd.kfx.Kfx
import io.github.hfhbd.kfx.VERSION
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskContainer
import javax.inject.Inject

abstract class Xsd : Kfx {
    abstract val schemaFiles: ConfigurableFileCollection

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

        dependencies.compiler.add("$GROUP:xsd-fir:$VERSION")

        val kfxXsdClasspath = configurations.resolvable("kfxXsdClasspath$serviceName") {
            it.fromDependencyCollector(this@Xsd.dependencies.compiler)
        }
        val kotlinSourceDirectorySet = (sourceSet as ExtensionAware).extensions.getByName(
            "kotlin",
        ) as SourceDirectorySet
        kotlinSourceDirectorySet.srcDir(
            tasks.register("convertXsdFiles$serviceName", ConvertXsdFiles::class.java) {
                it.classpath.from(kfxXsdClasspath)
                it.schemaFiles.from(this@Xsd.schemaFiles)
                it.outputDirectory.convention(it.project.layout.buildDirectory.dir("generated/kfx/xsd/$serviceName"))
            },
        )
    }
}
