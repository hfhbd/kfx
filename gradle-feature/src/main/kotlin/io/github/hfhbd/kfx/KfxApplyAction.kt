package io.github.hfhbd.kfx

import io.github.hfhbd.kfx.openapi.ConvertOpenApiFiles
import io.github.hfhbd.kfx.swagger.ConvertSwaggerFiles
import io.github.hfhbd.kfx.wsdl.ConvertWsdlFiles
import io.github.hfhbd.kfx.xsd.ConvertXsdFiles
import org.gradle.api.file.SourceDirectorySet
import org.gradle.features.file.ProjectFeatureLayout
import org.gradle.features.registration.ConfigurationRegistrar
import org.gradle.features.registration.TaskRegistrar
import javax.inject.Inject

abstract class KfxApplyAction {
    @get:Inject
    internal abstract val configurations: ConfigurationRegistrar

    @get:Inject
    internal abstract val tasks: TaskRegistrar

    @get:Inject
    internal abstract val layout: ProjectFeatureLayout

    fun apply(
        definition: KfxDefinition,
        sourceDirectorySet: SourceDirectorySet,
    ) {
        definition.openApi.all { openApi ->
            configure(openApi, sourceDirectorySet)
        }

        definition.xsd.all { xsd ->
            configure(xsd, sourceDirectorySet)
        }

        definition.wsdl.all { wsdl ->
            @Suppress("INVISIBLE_REFERENCE")
            wsdl.dependencies.compiler.add("$GROUP:wsdl-fir:$VERSION")

            val kfxWsdlClasspath = configurations.resolvable("kfxWsdlClasspath${wsdl.name}") {
                it.fromDependencyCollector(wsdl.dependencies.compiler)
            }

            sourceDirectorySet.srcDir(
                tasks.register("convertWsdlFiles${wsdl.name}", ConvertWsdlFiles::class.java) {
                    @Suppress("INVISIBLE_REFERENCE")
                    it.classpath.from(kfxWsdlClasspath)
                    it.wsdlFiles.from(wsdl.wsdlFiles)
                    it.schemaFiles.from(wsdl.schemaFiles)
                    it.outputDirectory.convention(
                        layout.contextBuildDirectory.map { it.dir("generated/kfx/wsdl/${wsdl.name}") },
                    )
                },
            )
        }

        definition.swagger.all { swagger ->
            @Suppress("INVISIBLE_REFERENCE")
            swagger.dependencies.compiler.add("$GROUP:swagger-fir:$VERSION")
            @Suppress("INVISIBLE_REFERENCE")
            swagger.dependencies.compiler.add("$GROUP:ir-packagename:$VERSION")

            val kfxSwaggerClasspath = configurations.resolvable("kfxSwaggerClasspath${swagger.name}") {
                it.fromDependencyCollector(swagger.dependencies.compiler)
            }

            sourceDirectorySet.srcDir(
                tasks.register("convertSwaggerFiles${swagger.name}", ConvertSwaggerFiles::class.java) {
                    @Suppress("INVISIBLE_REFERENCE")
                    it.classpath.from(kfxSwaggerClasspath)
                    it.swaggerFiles.from(swagger.files)
                    it.outputDirectory.convention(
                        layout.contextBuildDirectory.map { it.dir("generated/kfx/swagger/${swagger.name}") },
                    )
                    it.packageName.convention(swagger.packageName)
                },
            )
        }
    }

    private fun configure(openApi: OpenApi, sourceDirectorySet: SourceDirectorySet) {
        @Suppress("INVISIBLE_REFERENCE")
        openApi.dependencies.compiler.add("$GROUP:openapi-fir:$VERSION")
        @Suppress("INVISIBLE_REFERENCE")
        openApi.dependencies.compiler.add("$GROUP:ir-packagename:$VERSION")

        val kfxOpenApiClasspath = configurations.resolvable("kfxOpenApiClasspath${openApi.name}") {
            it.fromDependencyCollector(openApi.dependencies.compiler)
        }

        sourceDirectorySet.srcDir(
            tasks.register("convertOpenApiFiles${openApi.name}", ConvertOpenApiFiles::class.java) {
                @Suppress("INVISIBLE_REFERENCE")
                it.classpath.from(kfxOpenApiClasspath)
                it.openapiFiles.from(openApi.files)
                it.outputDirectory.convention(
                    layout.contextBuildDirectory.map { it.dir("generated/kfx/openapi/${openApi.name}/") },
                )
                it.packageName.set(openApi.packageName)
            },
        )
    }

    private fun configure(xsd: Xsd, sourceDirectorySet: SourceDirectorySet) {
        @Suppress("INVISIBLE_REFERENCE")
        xsd.dependencies.compiler.add("$GROUP:xsd-fir:$VERSION")

        val kfxXsdClasspath = configurations.resolvable("kfxXsdClasspath${xsd.name}") {
            it.fromDependencyCollector(xsd.dependencies.compiler)
        }

        sourceDirectorySet.srcDir(
            tasks.register("convertXsdFiles${xsd.name}", ConvertXsdFiles::class.java) {
                @Suppress("INVISIBLE_REFERENCE")
                it.classpath.from(kfxXsdClasspath)
                it.schemaFiles.from(xsd.schemaFiles)
                it.outputDirectory.convention(
                    layout.contextBuildDirectory.map { it.dir("generated/kfx/xsd/${xsd.name}") },
                )
            },
        )
    }
}
