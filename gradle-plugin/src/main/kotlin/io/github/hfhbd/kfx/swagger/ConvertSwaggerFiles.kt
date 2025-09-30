package io.github.hfhbd.kfx.swagger

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
abstract class ConvertSwaggerFiles : DefaultTask() {
    init {
        group = "kfx"
    }

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    @get:SkipWhenEmpty
    @get:IgnoreEmptyDirectories
    abstract val swaggerFiles: ConfigurableFileCollection

    @get:Input
    @get:Optional
    abstract val packageName: Property<String>

    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    @get:Inject
    internal abstract val workerExecutor: WorkerExecutor

    @get:Classpath
    internal abstract val classpath: ConfigurableFileCollection

    init {
        outputDirectory.convention(project.layout.buildDirectory.dir("generated/kfx/swagger"))
    }

    @TaskAction
    internal fun generate() {
        val workQueue = workerExecutor.classLoaderIsolation {
            it.classpath.from(this@ConvertSwaggerFiles.classpath)
        }
        for (swaggerFile in swaggerFiles.asFileTree.files) {
            workQueue.submit(SwaggerGeneration::class.java) {
                it.packageName.set(packageName)
                it.swaggerFile.set(swaggerFile)
                it.outputDirectory.set(outputDirectory)
            }
        }
    }
}
