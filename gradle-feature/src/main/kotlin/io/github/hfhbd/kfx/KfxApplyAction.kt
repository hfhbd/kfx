package io.github.hfhbd.kfx

import io.github.hfhbd.kfx.openapi.ConvertOpenApiFiles
import io.github.hfhbd.kfx.swagger.ConvertSwaggerFiles
import io.github.hfhbd.kfx.wsdl.ConvertWsdlFiles
import org.gradle.api.artifacts.dsl.DependencyFactory
import org.gradle.api.file.SourceDirectorySet
import org.gradle.features.binding.ProjectFeatureApplicationContext
import org.gradle.features.file.ProjectFeatureLayout
import org.gradle.features.registration.ConfigurationRegistrar
import org.gradle.features.registration.TaskRegistrar
import javax.inject.Inject

abstract class KfxApplyAction {
    @get:Inject abstract val configurations: ConfigurationRegistrar

    @get:Inject abstract val tasks: TaskRegistrar

    @get:Inject abstract val layout: ProjectFeatureLayout

    @get:Inject abstract val dependencyFactory: DependencyFactory

    fun ProjectFeatureApplicationContext.apply(
        definition: KfxDefinition,
        buildModel: KfxBuildModel,
        sourceDirectorySet: SourceDirectorySet,
    ) {
        buildModel.openApi.all { openApiBuildModel ->
            @Suppress("INVISIBLE_REFERENCE")
            openApiBuildModel.compilerDependencies.configure {
                it.dependencies.add(dependencyFactory.create("$GROUP:openapi-fir:$VERSION"))
                it.dependencies.add(dependencyFactory.create("$GROUP:ir-packagename:$VERSION"))
            }

            val classpath = configurations.resolvable("kfxOpenApiClasspath${openApiBuildModel.name}") {
                it.extendsFrom(openApiBuildModel.compilerDependencies)
            }

            openApiBuildModel.outputDirectory.convention(
                layout.contextBuildDirectory.map { it.dir("generated/kfx/openapi/${openApiBuildModel.name}/") },
            )

            val task = tasks.register("convertOpenApiFiles${openApiBuildModel.name}", ConvertOpenApiFiles::class.java) {
                @Suppress("INVISIBLE_REFERENCE")
                it.classpath.from(classpath)
                it.openapiFiles.from(openApiBuildModel.files)
                it.outputDirectory.convention(openApiBuildModel.outputDirectory)
                it.packageName.set(openApiBuildModel.packageName)
            }
            sourceDirectorySet.srcDir(task)
        }

        for (openApi in definition.openApi) {
            val openApiBuildModel = getBuildModel(openApi)
            openApiBuildModel as DefaultOpenApiBuildModel
            // Move to constructor after https://github.com/gradle/gradle/issues/38207
            openApiBuildModel.internalName = openApi.name
            openApiBuildModel.compilerDependencies = configurations.dependencyScope(
                "kfxOpenApi${openApiBuildModel.name}",
            )

            openApiBuildModel.compilerDependencies.configure {
                it.fromDependencyCollector(openApi.dependencies.compiler)
            }
            openApiBuildModel.files.from(openApi.files)
            openApiBuildModel.packageName.convention(openApi.packageName)

            buildModel.openApi.add(openApiBuildModel)
        }

        buildModel.swagger.all { swaggerBuildModel ->
            @Suppress("INVISIBLE_REFERENCE")
            swaggerBuildModel.compilerDependencies.configure {
                it.dependencies.add(dependencyFactory.create("$GROUP:swagger-fir:$VERSION"))
                it.dependencies.add(dependencyFactory.create("$GROUP:ir-packagename:$VERSION"))
            }

            val classpath = configurations.resolvable("kfxSwaggerClasspath${swaggerBuildModel.name}") {
                it.extendsFrom(swaggerBuildModel.compilerDependencies)
            }

            swaggerBuildModel.outputDirectory.convention(
                layout.contextBuildDirectory.map { it.dir("generated/kfx/swagger/${swaggerBuildModel.name}/") },
            )

            val task = tasks.register("convertSwaggerFiles${swaggerBuildModel.name}", ConvertSwaggerFiles::class.java) {
                @Suppress("INVISIBLE_REFERENCE")
                it.classpath.from(classpath)
                it.swaggerFiles.from(swaggerBuildModel.files)
                it.outputDirectory.convention(swaggerBuildModel.outputDirectory)
                it.packageName.set(swaggerBuildModel.packageName)
            }
            sourceDirectorySet.srcDir(task)
        }

        for (swagger in definition.swagger) {
            val swaggerBuildModel = getBuildModel(swagger)
            swaggerBuildModel as DefaultSwaggerBuildModel
            // Move to constructor after https://github.com/gradle/gradle/issues/38207
            swaggerBuildModel.internalName = swagger.name
            swaggerBuildModel.compilerDependencies = configurations.dependencyScope(
                "kfxSwagger${swaggerBuildModel.name}",
            )

            swaggerBuildModel.compilerDependencies.configure {
                it.fromDependencyCollector(swagger.dependencies.compiler)
            }
            swaggerBuildModel.files.from(swagger.files)
            swaggerBuildModel.packageName.convention(swagger.packageName)

            buildModel.swagger.add(swaggerBuildModel)
        }

        buildModel.wsdl.all { wsdlBuildModel ->
            @Suppress("INVISIBLE_REFERENCE")
            wsdlBuildModel.compilerDependencies.configure {
                it.dependencies.add(dependencyFactory.create("$GROUP:wsdl-fir:$VERSION"))
            }

            val classpath = configurations.resolvable("kfxWsdlClasspath${wsdlBuildModel.name}") {
                it.extendsFrom(wsdlBuildModel.compilerDependencies)
            }

            wsdlBuildModel.outputDirectory.convention(
                layout.contextBuildDirectory.map { it.dir("generated/kfx/wsdl/${wsdlBuildModel.name}/") },
            )

            val task = tasks.register("convertWsdlFiles${wsdlBuildModel.name}", ConvertWsdlFiles::class.java) {
                @Suppress("INVISIBLE_REFERENCE")
                it.classpath.from(classpath)
                it.wsdlFiles.from(wsdlBuildModel.wsdlFiles)
                it.schemaFiles.from(wsdlBuildModel.schemaFiles)
                it.outputDirectory.convention(wsdlBuildModel.outputDirectory)
            }
            sourceDirectorySet.srcDir(task)
        }

        for (wsdl in definition.wsdl) {
            val wsdlBuildModel = getBuildModel(wsdl)
            wsdlBuildModel as DefaultWsdlBuildModel
            // Move to constructor after https://github.com/gradle/gradle/issues/38207
            wsdlBuildModel.internalName = wsdl.name
            wsdlBuildModel.compilerDependencies = configurations.dependencyScope("kfxWsdl${wsdlBuildModel.name}")

            wsdlBuildModel.compilerDependencies.configure {
                it.fromDependencyCollector(wsdl.dependencies.compiler)
            }
            wsdlBuildModel.wsdlFiles.from(wsdl.wsdlFiles)
            wsdlBuildModel.schemaFiles.from(wsdl.schemaFiles)

            buildModel.wsdl.add(wsdlBuildModel)
        }

        buildModel.xsd.all { xsdBuildModel ->
            @Suppress("INVISIBLE_REFERENCE")
            xsdBuildModel.compilerDependencies.configure {
                it.dependencies.add(dependencyFactory.create("$GROUP:xsd-fir:$VERSION"))
            }

            val classpath = configurations.resolvable("kfxXsdClasspath${xsdBuildModel.name}") {
                it.extendsFrom(xsdBuildModel.compilerDependencies)
            }

            xsdBuildModel.outputDirectory.convention(
                layout.contextBuildDirectory.map { it.dir("generated/kfx/xsd/${xsdBuildModel.name}/") },
            )

            val task = tasks.register("convertXsdFiles${xsdBuildModel.name}", ConvertWsdlFiles::class.java) {
                @Suppress("INVISIBLE_REFERENCE")
                it.classpath.from(classpath)
                it.schemaFiles.from(xsdBuildModel.schemaFiles)
                it.outputDirectory.convention(xsdBuildModel.outputDirectory)
            }
            sourceDirectorySet.srcDir(task)
        }

        for (xsd in definition.xsd) {
            val xsdBuildModel = getBuildModel(xsd)
            xsdBuildModel as DefaultXsdBuildModel
            // Move to constructor after https://github.com/gradle/gradle/issues/38207
            xsdBuildModel.internalName = xsd.name
            xsdBuildModel.compilerDependencies = configurations.dependencyScope("kfxXsd${xsdBuildModel.name}")

            xsdBuildModel.compilerDependencies.configure {
                it.fromDependencyCollector(xsd.dependencies.compiler)
            }
            xsdBuildModel.schemaFiles.from(xsd.schemaFiles)

            buildModel.xsd.add(xsdBuildModel)
        }
    }
}
