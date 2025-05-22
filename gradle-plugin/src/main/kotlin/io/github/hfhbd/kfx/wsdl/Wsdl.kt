package io.github.hfhbd.kfx.wsdl

import io.github.hfhbd.kfx.GROUP
import io.github.hfhbd.kfx.Kfx
import io.github.hfhbd.kfx.KfxDependencies
import io.github.hfhbd.kfx.VERSION
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.register
import javax.inject.Inject

abstract class Wsdl : Kfx {
    abstract val wsdlFiles: ConfigurableFileCollection
    abstract val schemaFiles: ConfigurableFileCollection

    @get:Nested
    abstract override val dependencies: KfxDependencies

    @get:Inject
    internal abstract val configurations: ConfigurationContainer

    @get:Inject
    internal abstract val tasks: TaskContainer

    override fun usingSourceSet(sourceSet: SourceDirectorySet) {
        val serviceName: String = name

        dependencies.compiler.add("$GROUP:wsdl-fir:$VERSION")

        val kfxWsdl = configurations.dependencyScope("kfxWsdl$serviceName") {
            fromDependencyCollector(this@Wsdl.dependencies.compiler)
        }
        val kfxWsdlClasspath = configurations.resolvable("kfxWsdlClasspath$serviceName") {
            extendsFrom(kfxWsdl.get())
        }
        sourceSet.srcDir(
            tasks.register("convertWsdlFiles$serviceName", ConvertWsdlFiles::class) {
                classpath.from(kfxWsdlClasspath)
                wsdlFiles.from(this@Wsdl.wsdlFiles)
                schemaFiles.from(this@Wsdl.schemaFiles)
                outputFolder.convention(project.layout.buildDirectory.dir("generated/kfx/wsdl/$serviceName"))
            },
        )
    }
}
