package io.github.hfhbd.kfx.openapi

import org.gradle.api.*
import org.gradle.api.file.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.gradle.workers.*
import javax.inject.*

@CacheableTask
abstract class ConvertOpenApiFiles : DefaultTask() {
    init {
        group = "kfx"
    }

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    @get:SkipWhenEmpty
    @get:IgnoreEmptyDirectories
    abstract val openapiFiles: ConfigurableFileCollection

    @get:OutputDirectory
    abstract val outputFolder: DirectoryProperty

    @get:Input
    @get:Optional
    abstract val packageName: Property<String>

    @get:Inject
    internal abstract val workerExecutor: WorkerExecutor

    @get:Classpath
    internal abstract val classpath: ConfigurableFileCollection

    @TaskAction
    internal fun generate() {
        val workQueue = workerExecutor.classLoaderIsolation {
            classpath.from(this@ConvertOpenApiFiles.classpath)
        }
        for (openapiFolder in openapiFiles) {
            for (openapiFile in openapiFolder.walk()) {
                if (openapiFile.isFile) {
                    workQueue.submit(OpenApiGeneration::class.java) {
                        this.openapiFile.set(openapiFile)
                        this.packageName.set(this@ConvertOpenApiFiles.packageName)
                        this.outputFolder.set(this@ConvertOpenApiFiles.outputFolder)
                    }
                }
            }
        }
    }
}
