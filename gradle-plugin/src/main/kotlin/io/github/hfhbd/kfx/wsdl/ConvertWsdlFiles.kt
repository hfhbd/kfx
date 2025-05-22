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
    abstract val outputFolder: DirectoryProperty

    @get:Inject
    internal abstract val workerExecutor: WorkerExecutor

    @get:Classpath
    internal abstract val classpath: ConfigurableFileCollection

    @TaskAction
    internal fun generate() {
        val workQueue = workerExecutor.classLoaderIsolation {
            classpath.from(this@ConvertWsdlFiles.classpath)
        }
        for (wsdlFolder in wsdlFiles) {
            for (wsdlFile in wsdlFolder.walk()) {
                if (wsdlFile.isFile) {
                    workQueue.submit(WsdlGeneration::class.java) {
                        this.wsdlFile.set(wsdlFile)
                        this.schemaFiles.setFrom(this@ConvertWsdlFiles.schemaFiles)
                        this.outputFolder.set(this@ConvertWsdlFiles.outputFolder)
                    }
                }
            }
        }
    }
}
