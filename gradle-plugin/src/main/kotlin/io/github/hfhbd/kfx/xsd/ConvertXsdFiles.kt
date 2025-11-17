package io.github.hfhbd.kfx.xsd

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.IgnoreEmptyDirectories
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.WorkerExecutor
import javax.inject.Inject

@CacheableTask
abstract class ConvertXsdFiles : DefaultTask() {
    init {
        group = "kfx"
    }

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    @get:IgnoreEmptyDirectories
    abstract val schemaFiles: ConfigurableFileCollection

    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    @get:Inject
    internal abstract val workerExecutor: WorkerExecutor

    @get:Classpath
    internal abstract val classpath: ConfigurableFileCollection

    @TaskAction
    internal fun generate() {
        val workQueue = workerExecutor.classLoaderIsolation {
            it.classpath.from(this@ConvertXsdFiles.classpath)
        }
        workQueue.submit(XsdGeneration::class.java) {
            it.xsdFiles.from(schemaFiles)
            it.outputDirectory.set(outputDirectory)
        }
    }
}
