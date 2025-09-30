package io.github.hfhbd.kfx.openapi

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.IgnoreEmptyDirectories
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.SkipWhenEmpty
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.WorkerExecutor
import javax.inject.Inject

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
        for (openapiFile in openapiFiles.asFileTree.files) {
            workQueue.submit(OpenApiGeneration::class.java) {
                it.openapiFile.set(openapiFile)
                it.packageName.set(packageName)
                it.outputDirectory.set(outputDirectory)
            }
        }
    }
}
