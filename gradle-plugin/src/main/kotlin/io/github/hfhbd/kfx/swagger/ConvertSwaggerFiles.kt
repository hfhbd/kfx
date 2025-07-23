package io.github.hfhbd.kfx.swagger

import org.gradle.api.*
import org.gradle.api.file.*
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.gradle.workers.*
import javax.inject.*

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
