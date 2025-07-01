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
    abstract val outputDirectory: DirectoryProperty

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
            it.classpath.from(this@ConvertOpenApiFiles.classpath)
        }
        for (openapiDirectory in openapiFiles) {
            for (openapiFile in openapiDirectory.walk()) {
                if (openapiFile.isFile) {
                    workQueue.submit(OpenApiGeneration::class.java) {
                        it.openapiFile.set(openapiFile)
                        it.packageName.set(packageName)
                        it.outputDirectory.set(outputDirectory)
                    }
                }
            }
        }
    }
}
