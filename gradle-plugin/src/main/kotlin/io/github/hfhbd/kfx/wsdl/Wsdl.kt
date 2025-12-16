package io.github.hfhbd.kfx.wsdl

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

abstract class Wsdl : Kfx {
    abstract val wsdlFiles: ConfigurableFileCollection
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

        dependencies.compiler.add("$GROUP:wsdl-fir:$VERSION")

        val kfxWsdlClasspath = configurations.resolvable("kfxWsdlClasspath$serviceName") {
            it.fromDependencyCollector(this@Wsdl.dependencies.compiler)
        }
        val kotlinSourceDirectorySet = (sourceSet as ExtensionAware).extensions.getByName(
            "kotlin",
        ) as SourceDirectorySet
        kotlinSourceDirectorySet.srcDir(
            tasks.register("convertWsdlFiles$serviceName", ConvertWsdlFiles::class.java) {
                it.classpath.from(kfxWsdlClasspath)
                it.wsdlFiles.from(this@Wsdl.wsdlFiles)
                it.schemaFiles.from(this@Wsdl.schemaFiles)
                it.outputDirectory.convention(it.project.layout.buildDirectory.dir("generated/kfx/wsdl/$serviceName"))
            },
        )
    }
}
