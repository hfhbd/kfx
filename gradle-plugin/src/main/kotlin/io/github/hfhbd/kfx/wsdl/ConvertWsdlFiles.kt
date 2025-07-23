package io.github.hfhbd.kfx.wsdl

import org.gradle.api.*
import org.gradle.api.file.*
import org.gradle.api.tasks.*
import org.gradle.workers.*
import javax.inject.*

@CacheableTask
abstract class ConvertWsdlFiles : DefaultTask() {
    init {
        group = "kfx"
    }

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    @get:SkipWhenEmpty
    @get:IgnoreEmptyDirectories
    abstract val wsdlFiles: ConfigurableFileCollection

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
            it.classpath.from(this@ConvertWsdlFiles.classpath)
        }
        for (wsdlFile in wsdlFiles.asFileTree.files) {
            workQueue.submit(WsdlGeneration::class.java) {
                it.wsdlFile.set(wsdlFile)
                it.schemaFiles.setFrom(schemaFiles)
                it.outputDirectory.set(outputDirectory)
            }
        }
    }
}
