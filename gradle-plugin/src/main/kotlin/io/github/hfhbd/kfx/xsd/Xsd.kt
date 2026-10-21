package io.github.hfhbd.kfx.xsd

import io.github.hfhbd.kfx.Kfx
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.tasks.TaskContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import javax.inject.Inject

abstract class Xsd : Kfx {
    abstract val schemaFiles: ConfigurableFileCollection

    @get:Inject
    internal abstract val configurations: ConfigurationContainer

    @get:Inject
    internal abstract val tasks: TaskContainer

    override fun usingKotlinSourceSet(kotlinSourceSet: KotlinSourceSet) {
        val serviceName = name

        dependencies.compiler.add(dependencies.kfxModule("xsd-fir"))

        val kfxXsdClasspath = configurations.resolvable("kfxXsdClasspath$serviceName") {
            it.fromDependencyCollector(this@Xsd.dependencies.compiler)
        }

        kotlinSourceSet.kotlin.srcDir(
            tasks.register("convertXsdFiles$serviceName", ConvertXsdFiles::class.java) {
                it.classpath.from(kfxXsdClasspath)
                it.schemaFiles.from(this@Xsd.schemaFiles)
                it.outputDirectory.convention(it.project.layout.buildDirectory.dir("generated/kfx/xsd/$serviceName"))
            },
        )
    }
}
