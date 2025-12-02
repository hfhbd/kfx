package io.github.hfhbd.kfx.openapi.fir

import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.StatusCode
import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.getStatusCodes
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.IRTree.Literal.BOOLEAN
import io.github.hfhbd.kfx.ir.IRTree.Literal.BYTE
import io.github.hfhbd.kfx.ir.IRTree.Literal.DATE
import io.github.hfhbd.kfx.ir.IRTree.Literal.DOUBLE
import io.github.hfhbd.kfx.ir.IRTree.Literal.DURATION
import io.github.hfhbd.kfx.ir.IRTree.Literal.FLOAT
import io.github.hfhbd.kfx.ir.IRTree.Literal.INSTANT
import io.github.hfhbd.kfx.ir.IRTree.Literal.INT
import io.github.hfhbd.kfx.ir.IRTree.Literal.LONG
import io.github.hfhbd.kfx.ir.IRTree.Literal.SHORT
import io.github.hfhbd.kfx.ir.IRTree.Literal.STRING
import io.github.hfhbd.kfx.ir.IRTree.Literal.UUID
import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.openapi.model.OpenApi
import io.github.hfhbd.kfx.openapi.model.OpenApi.Components.Schema
import io.github.hfhbd.kfx.openapi.model.json
import io.github.hfhbd.kfx.operationIdToCamelCase
import io.github.hfhbd.kfx.toCamelCase
import io.github.hfhbd.kfx.toCodeGen
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import java.nio.file.Path
import java.util.ServiceLoader
import kotlin.time.Duration
import kotlin.time.Instant
import kotlin.uuid.Uuid

fun generateOpenApi(
    openApiFile: InputStream,
    outputDirectory: Path,
    firTransformers: Iterable<OpenApiTransformer> = ServiceLoader.load(OpenApiTransformer::class.java),
    transformerFactories: Iterable<IrTransformer> = ServiceLoader.load(IrTransformer::class.java),
    codeGenCreator: CodeGenCreator = ServiceLoader.load(CodeGenCreator::class.java).single(),
    codeGenTransformer: Iterable<CodeGenTransformer> = ServiceLoader.load(CodeGenTransformer::class.java),
    codeGenerators: Iterable<CodeGenerator> = ServiceLoader.load(CodeGenerator::class.java),
) {
    val irTree = openApiFile.createIr(
        firTransformers,
    )

    val codeGenerator = irTree.toCodeGen(
        transformerFactories,
        codeGenCreator,
        codeGenTransformer,
    )
    for (codeGeneratorFactory in codeGenerators) {
        codeGeneratorFactory.generate(codeGenerator, outputDirectory)
    }
}

private fun InputStream.createIr(
    openapiTransformers: Iterable<OpenApiTransformer>,
): IRTree {
    val openapi = json.decodeFromStream(OpenApi.serializer(), this)
    val irTree = openapi.toIr(openapiTransformers)
    return irTree
}

private fun OpenApi.toIr(
    openapiTransformers: Iterable<OpenApiTransformer>,
): IRTree {
    val irTypes = mutableMapOf<String, IRTree.Class>()
    for ((name, type) in components.schemas) {
        when (type) {
            is Schema.OBJECT -> {
                val s = type.toIr(name, irTypes)
                if (s is IRTree.Class) {
                    irTypes[name] = s
                }
            }

            is Schema.ARRAY,
            is Schema.BOOLEAN,
            is Schema.INT,
            is Schema.NUMBER,
            -> continue

            is Schema.STRING -> {
                when (val irType = type.toIr(null, name, irTypes)) {
                    is IRTree.Enum -> irTypes[name] = irType
                    else -> continue
                }
            }
        }
    }

    val irOperations = mutableSetOf<IRTree.Operation>()
    for ((path, pathObject) in paths) {
        pathObject.head?.let {
            irOperations.add(
                it.toIr(
                    path,
                    pathObject.parameters,
                    components.parameters,
                    components.headers,
                    components.responses,
                    irTypes,
                    IRTree.Operation.HttpMethod.Head,
                ),
            )
        }
        pathObject.get?.let {
            irOperations.add(
                it.toIr(
                    path,
                    pathObject.parameters,
                    components.parameters,
                    components.headers,
                    components.responses,
                    irTypes,
                    IRTree.Operation.HttpMethod.Get,
                ),
            )
        }
        pathObject.post?.let {
            irOperations.add(
                it.toIr(
                    path,
                    pathObject.parameters,
                    components.parameters,
                    components.headers,
                    components.responses,
                    irTypes,
                    IRTree.Operation.HttpMethod.Post,
                ),
            )
        }
        pathObject.put?.let {
            irOperations.add(
                it.toIr(
                    path,
                    pathObject.parameters,
                    components.parameters,
                    components.headers,
                    components.responses,
                    irTypes,
                    IRTree.Operation.HttpMethod.Put,
                ),
            )
        }
        pathObject.patch?.let {
            irOperations.add(
                it.toIr(
                    path,
                    pathObject.parameters,
                    components.parameters,
                    components.headers,
                    components.responses,
                    irTypes,
                    IRTree.Operation.HttpMethod.Patch,
                ),
            )
        }
        pathObject.delete?.let {
            irOperations.add(
                it.toIr(
                    path,
                    pathObject.parameters,
                    components.parameters,
                    components.headers,
                    components.responses,
                    irTypes,
                    IRTree.Operation.HttpMethod.Delete,
                ),
            )
        }
    }

    var irTree = IRTree(
        irTypes.values.toSet(),
        irOperations,
        auth = components.securitySchemes.flatMapTo(mutableSetOf()) {
            toAuth(it.key, it.value)
        },
    )

    irTree = handleSealedClassMapping(irTree, this)
    for (openapiTransformer in openapiTransformers) {
        irTree = openapiTransformer(this, irTree)
    }
    return irTree
}

private fun OpenApi.Operation.toIr(
    path: String,
    pathParameters: List<OpenApi.Parameter>,
    componentParameters: Map<String, OpenApi.Parameter>,
    componentHeaders: Map<String, OpenApi.Operation.Header>,
    componentsResponses: Map<String, OpenApi.Operation.Response>,
    irTypes: MutableMap<String, IRTree.Class>,
    method: IRTree.Operation.HttpMethod,
): IRTree.Operation {
    val parameters = parameters + pathParameters

    val statusCodes = responses.keys.getStatusCodes()

    val doc = when {
        summary == null && description == null -> null
        summary != null && description == null -> summary
        summary == null && description != null -> description
        else -> "$summary\n$description"
    }

    val name = id.operationIdToCamelCase()
    val inputSchema = requestBody?.content?.entries?.firstOrNull()?.value?.schema?.takeUnless { it.isUnit() }
    var input = inputSchema?.toIr(
        parentName = name,
        name = name,
        irTypes,
    )
    if (input != null && input is IRTree.Class && input.qName !in irTypes) {
        input = when (input) {
            is IRTree.Enum -> input.copy(name = input.name + "Request")
            is IRTree.NormalClass -> input.copy(name = input.name + "Request")
        }
        irTypes[id + "Request"] = input
    }
    var output = responses[statusCodes.success]?.toIr(
        name,
        componentsResponses,
        irTypes,
    )
    if (output != null && output is IRTree.Class && output.qName !in irTypes) {
        output = when (output) {
            is IRTree.Enum -> output.copy(name = output.name + "Response")
            is IRTree.NormalClass -> output.copy(name = output.name + "Response")
        }
        irTypes[id + "Response"] = output
    }
    return IRTree.Operation(
        packageName = "",
        name = name.replaceFirstChar { it.lowercase() },
        documentation = doc,
        method = method,
        location = null,
        soapAction = null,
        path = path.replace("{", "\${"),
        input = input,
        inputContentType = requestBody?.content?.entries?.first()?.key?.let { ContentType.fromString(it) },
        output = output,
        outputContentType = responses[statusCodes.success]?.content?.entries?.firstOrNull()?.key?.let {
            ContentType.fromString(it)
        },
        outputHeaders = responses[statusCodes.success]?.headers?.map {
            it.value.toParameter(it.key, componentHeaders, irTypes)
        } ?: emptyList(),
        fault = responses[statusCodes.fault]?.let {
            val s = (
                it.toIr(
                    name,
                    componentsResponses,
                    irTypes,
                ) as IRTree.NormalClass?
                )?.copy(isFault = true) ?: return@let null

            val className = if (s.packageName.isEmpty()) s.name else s.packageName + "." + s.name
            irTypes[className] = s
            s
        },
        faultHeaders = responses[statusCodes.fault]?.headers?.map {
            it.value.toParameter(it.key, componentHeaders, irTypes)
        } ?: emptyList(),
        parameters = parameters.mapNotNull {
            when (it.position) {
                OpenApi.Parameter.Position.Cookie -> null
                OpenApi.Parameter.Position.Query -> null

                OpenApi.Parameter.Position.Header -> null

                OpenApi.Parameter.Position.Path,
                -> it.toParameter(componentParameters, irTypes).second.copy(
                    nullable = false,
                )

                null -> it.toParameter(
                    componentParameters,
                    irTypes,
                ).takeIf {
                    when (it.first) {
                        OpenApi.Parameter.Position.Path -> {
                            true
                        }

                        else -> {
                            false
                        }
                    }
                }?.second?.copy(
                    nullable = false,
                )
            }
        },
        queryParameters = parameters.mapNotNull {
            when (it.position) {
                OpenApi.Parameter.Position.Header -> null
                OpenApi.Parameter.Position.Cookie -> null
                OpenApi.Parameter.Position.Path -> null
                OpenApi.Parameter.Position.Query -> it.toParameter(
                    componentParameters,
                    irTypes,
                ).second.copy(
                    nullable = true,
                )

                null -> it.toParameter(
                    componentParameters,
                    irTypes,
                ).takeIf { it.first == OpenApi.Parameter.Position.Query }?.second?.copy(
                    nullable = true,
                )
            }
        },
        success = statusCodes.success?.toIntOrNull()?.let { StatusCode.fromValue(it) },
        notFound = "404" in responses.keys,
        deprecated = deprecated,
        headers = parameters.mapNotNull {
            when (it.position) {
                OpenApi.Parameter.Position.Query -> null
                OpenApi.Parameter.Position.Cookie -> null
                OpenApi.Parameter.Position.Path -> null
                OpenApi.Parameter.Position.Header -> it.toParameter(componentParameters, irTypes).second

                null -> it.toParameter(
                    componentParameters,
                    irTypes,
                ).takeIf { it.first == OpenApi.Parameter.Position.Header }?.second
            }
        },
    )
}

private fun OpenApi.Operation.Response.toIr(
    name: String,
    responses: Map<String, OpenApi.Operation.Response>,
    irTypes: MutableMap<String, IRTree.Class>,
): IRTree.Type? = if (ref != null) {
    val response = responses[ref!!.removePrefix("#/components/responses/")]!!
    response.toIr(name, responses, irTypes)
} else {
    val schema = content.values.firstOrNull()?.schema ?: return null
    if (schema is Schema.OBJECT && schema.ref != null) {
        irTypes.find(schema.ref!!)
    } else if (schema.isUnit()) {
        null
    } else {
        schema.toIr(name, name, irTypes)
    }
}

private fun toAuth(name: String, definition: OpenApi.SecurityScheme): List<IRTree.Auth> = when (definition) {
    is OpenApi.SecurityScheme.OAuth2 -> buildList {
        val clientCredentials = definition.flows.clientCredentials
        if (clientCredentials != null) {
            add(
                IRTree.Auth.OAuth2(
                    flow = IRTree.Auth.OAuth2.Flow.Application,
                    operation = IRTree.Operation(
                        packageName = "",
                        name = name,
                        documentation = definition.description,
                        IRTree.Operation.HttpMethod.Post,
                        path = clientCredentials.tokenUrl,
                        parameters = listOf(
                            IRTree.Operation.Parameter(
                                name = "clientId",
                                serialName = null,
                                type = IRTree.Type.Builtin.STRING,
                                nullable = false,
                                documentation = null,
                                defaultValue = null,
                                deprecated = false,
                            ),
                            IRTree.Operation.Parameter(
                                name = "clientSecret",
                                serialName = null,
                                type = IRTree.Type.Builtin.STRING,
                                nullable = false,
                                documentation = null,
                                defaultValue = null,
                                deprecated = false,
                            ),
                        ),
                        soapAction = null,
                        queryParameters = emptyList(),
                        fault = null,
                        faultHeaders = emptyList(),
                        input = null,
                        inputContentType = null,
                        output = OAuth2Token,
                        outputContentType = ContentType.ApplicationJson,
                        outputHeaders = emptyList(),
                        location = null,
                        success = StatusCode.OK,
                        notFound = false,
                        headers = emptyList(),
                        deprecated = false,
                    ),
                    grantType = IRTree.Auth.OAuth2.GrantType.ClientCredentials,
                ),
            )
        }
    }

    is OpenApi.SecurityScheme.ApiKey -> TODO()
    is OpenApi.SecurityScheme.Http -> listOf(
        IRTree.Auth.Http(
            schema = when (definition.scheme) {
                OpenApi.SecurityScheme.Http.Scheme.Basic -> IRTree.Auth.Http.Schema.Basic
                OpenApi.SecurityScheme.Http.Scheme.Bearer -> IRTree.Auth.Http.Schema.Bearer
            },
            name = name,
            packageName = "",
            documentation = definition.description,
        ),
    )

    is OpenApi.SecurityScheme.MutualTLS -> emptyList()
    is OpenApi.SecurityScheme.OpenIdConnect -> TODO()
}

private val OAuth2Token = IRTree.NormalClass(
    packageName = "io.github.hfhbd.kfx.oauth2",
    name = "OAuth2Token",
    packageNameSuffix = "",
    documentation = null,
    serialName = null,
    namespace = null,
    members = emptyMap(),
    isFault = false,
    discriminator = null,
    allOf = null,
    deprecated = false,
)

private fun OpenApi.Parameter.toParameter(
    parameters: Map<String, OpenApi.Parameter>,
    irTypes: MutableMap<String, IRTree.Class>,
): Pair<OpenApi.Parameter.Position?, IRTree.Operation.Parameter> {
    if (ref != null) {
        val name = ref!!.removePrefix("#/components/parameters/")
        val found = parameters[name]!!

        return found.position to IRTree.Operation.Parameter(
            name = found.name!!,
            type = found.schema!!.toIr(name, name, irTypes),
            nullable = !found.required,
            documentation = found.description,
            serialName = null,
            defaultValue = found.schema!!.toIrDefault(),
            deprecated = deprecated,
        )
    } else {
        return null to IRTree.Operation.Parameter(
            name = name!!,
            type = schema!!.toIr(name, name, irTypes),
            nullable = !required,
            documentation = description,
            serialName = null,
            defaultValue = schema!!.toIrDefault(),
            deprecated = deprecated,
        )
    }
}

private fun OpenApi.Operation.Header.toParameter(
    headerName: String,
    headers: Map<String, OpenApi.Operation.Header>,
    irTypes: MutableMap<String, IRTree.Class>,
): IRTree.Operation.Parameter {
    if (ref != null) {
        val name = ref!!.removePrefix("#/components/headers/")
        val found = headers[name]!!

        return IRTree.Operation.Parameter(
            name = headerName,
            type = found.schema!!.toIr(name, name, irTypes),
            nullable = !found.required,
            documentation = found.description,
            serialName = null,
            defaultValue = found.schema!!.toIrDefault(),
            deprecated = deprecated,
        )
    } else {
        return IRTree.Operation.Parameter(
            name = headerName,
            type = schema!!.toIr(null, headerName, irTypes),
            nullable = !required,
            documentation = description,
            serialName = null,
            defaultValue = schema!!.toIrDefault(),
            deprecated = deprecated,
        )
    }
}

private fun Schema.toIrDefault(): IRTree.Literal? = when (this) {
    is Schema.STRING -> when (format) {
        Schema.STRING.Format.Byte -> null
        Schema.STRING.Format.Binary -> null
        Schema.STRING.Format.Date -> default?.let { DATE(LocalDate.parse(it)) }
        Schema.STRING.Format.DateTime -> default?.let { INSTANT(Instant.parse(it)) }
        Schema.STRING.Format.Password -> null
        Schema.STRING.Format.Duration -> default?.let { DURATION(Duration.parse(it)) }
        Schema.STRING.Format.Uuid -> default?.let { UUID(Uuid.parse(it)) }
        else -> default?.let { STRING(it) }
    }

    is Schema.INT -> when (format) {
        Schema.INT.Format.Int32 -> default?.let { INT(it.toInt()) }
        Schema.INT.Format.Int64 -> default?.let { LONG(it) }
        Schema.INT.Format.Int8 -> default?.let { BYTE(it.toByte()) }
        Schema.INT.Format.Int16 -> default?.let { SHORT(it.toShort()) }
    }

    is Schema.BOOLEAN -> default?.let { BOOLEAN(it) }
    is Schema.OBJECT -> null
    is Schema.ARRAY -> null
    is Schema.NUMBER -> when (format) {
        Schema.NUMBER.Format.Float -> default?.let { FLOAT(it.toFloat()) }
        Schema.NUMBER.Format.Double -> default?.let { DOUBLE(it) }
        Schema.NUMBER.Format.Decimal -> default?.let { DOUBLE(it) }
    }
}

private fun Map<String, IRTree.Class>.findOrNull(id: String): IRTree.Class? {
    val id = id.removePrefix("#/components/schemas/")
    return this[id]
}

private fun Map<String, IRTree.Class>.find(id: String): IRTree.Class = findOrNull(id) ?: error("$id not in $keys")

private fun Schema.toIr(
    parentName: String?,
    name: String?,
    irTypes: MutableMap<String, IRTree.Class>,
): IRTree.Type = when (this) {
    is Schema.ARRAY -> toIr(parentName!!, name?.replaceFirstChar { it.uppercaseChar() } ?: "Items", irTypes)
    is Schema.BOOLEAN -> toIr()
    is Schema.INT -> toIr()
    is Schema.NUMBER -> toIr()
    is Schema.OBJECT -> toIr(name, irTypes)
    is Schema.STRING -> toIr(parentName, name, irTypes)
}

private fun Schema.BOOLEAN.toIr() = IRTree.Type.Builtin.BOOLEAN

private fun Schema.ARRAY.toIr(
    parentName: String,
    suffix: String,
    irTypes: MutableMap<String, IRTree.Class>,
): IRTree.Type.LIST {
    val items = items
    return IRTree.Type.LIST(
        if (items is Schema.ARRAY) {
            items.toIr(parentName, suffix, irTypes)
        } else {
            items?.toIr(parentName, parentName + suffix, irTypes) ?: irTypes.find(ref!!)
        },
    )
}

private fun Schema.INT.toIr() = when (format) {
    Schema.INT.Format.Int32 -> IRTree.Type.Builtin.INT
    Schema.INT.Format.Int64 -> IRTree.Type.Builtin.LONG
    Schema.INT.Format.Int8 -> IRTree.Type.Builtin.BYTE
    Schema.INT.Format.Int16 -> IRTree.Type.Builtin.SHORT
}

private fun Schema.NUMBER.toIr() = when (format) {
    Schema.NUMBER.Format.Double -> IRTree.Type.Builtin.DOUBLE
    Schema.NUMBER.Format.Float -> IRTree.Type.Builtin.FLOAT
    Schema.NUMBER.Format.Decimal -> IRTree.Type.Builtin.DOUBLE
}

private fun Schema.STRING.toIr(
    parentName: String?,
    name: String?,
    irTypes: MutableMap<String, IRTree.Class>,
): IRTree.Type = if (enum.isNotEmpty()) {
    val name = (parentName ?: "") + name!!.toCamelCase().replaceFirstChar {
        it.uppercaseChar()
    }
    val className = name.asClassName()
    val enum = IRTree.Enum(
        name = className.name,
        packageName = className.packageName,
        packageNameSuffix = "",
        documentation = description,
        deprecated = deprecated,
        values = enum.filterNotNull().map {
            IRTree.Enum.Value(it, null, it)
        },
    )
    irTypes[enum.name] = enum
    enum
} else {
    when (format) {
        Schema.STRING.Format.Byte -> IRTree.Type.Builtin.BYTESTRING
        Schema.STRING.Format.Binary -> IRTree.Type.Builtin.BINARY
        Schema.STRING.Format.Date -> IRTree.Type.DateType.DATE
        Schema.STRING.Format.DateTime -> IRTree.Type.DateType.INSTANT
        Schema.STRING.Format.Password -> IRTree.Type.Builtin.STRING
        Schema.STRING.Format.Duration -> IRTree.Type.Builtin.DURATION
        Schema.STRING.Format.Uuid -> IRTree.Type.Builtin.UUID
        else -> IRTree.Type.Builtin.STRING
    }
}

private fun Schema.isUnit(): Boolean =
    this is Schema.OBJECT && ref == null && properties.isEmpty() && additionalPropertiesSchema == null

private fun Schema.OBJECT.asClassName(name: String?): IRTree.ClassName = (
    ref?.removePrefix(
        "#/components/schemas/",
    ) ?: name!!
    ).asClassName()

private fun String.asClassName(): IRTree.ClassName = if ("." in this) {
    val qName = split(".")
    IRTree.ClassName(
        qName.dropLast(1).joinToString(".") {
            it.lowercase()
        },
        qName.last().replaceFirstChar { it.uppercaseChar() },
    )
} else {
    IRTree.ClassName("", replaceFirstChar { it.uppercaseChar() })
}

private fun Schema.OBJECT.toIr(
    name: String?,
    irTypes: MutableMap<String, IRTree.Class>,
): IRTree.Type {
    val resolvedRef = asClassName(name)
    val discriminator = discriminator?.propertyName
    if (additionalPropertiesSchema != null && properties.isEmpty()) {
        return IRTree.Type.MAP(
            key = IRTree.Type.Builtin.STRING,
            value = additionalPropertiesSchema!!.toIr(
                parentName = resolvedRef.name,
                name = resolvedRef.name,
                irTypes = irTypes,
            ),
        )
    } else {
        return IRTree.NormalClass(
            packageName = resolvedRef.packageName,
            packageNameSuffix = "",
            name = resolvedRef.name,
            serialName = null,
            namespace = null,
            members = buildMap {
                putAll(
                    properties.filterNot {
                        if (discriminator != null) {
                            it.key == discriminator
                        } else {
                            false
                        }
                    }.toMembers(
                        name = name,
                        irTypes = irTypes,
                        required = required,
                    ),
                )

                for (it in allOf) {
                    val it = it as Schema.OBJECT
                    if (it.ref == null) {
                        putAll(
                            it.properties.toMembers(
                                irTypes = irTypes,
                                required = it.required,
                            ),
                        )
                    } else {
                        val irClass = irTypes.findOrNull(it.ref!!) as IRTree.NormalClass?
                        if (irClass != null) {
                            putAll(irClass.members)
                        } else {
                            val toIR = it.toIr(name, irTypes)
                            if (toIR is IRTree.Class) {
                                irTypes[it.ref!!.removePrefix("#/components/schemas/")] = toIR
                            }
                        }
                    }
                }
            },
            documentation = description,
            isFault = false,
            discriminator = discriminator,
            deprecated = deprecated,
            allOf = allOf.mapNotNull {
                val ref = (it as Schema.OBJECT).ref
                if (ref != null) {
                    it.asClassName(null)
                } else {
                    null
                }
            }.singleOrNull(),
        )
    }
}

private fun Map<String, Schema>.toMembers(
    name: String? = null,
    irTypes: MutableMap<String, IRTree.Class>,
    required: List<String>? = null,
): Map<String, IRTree.Member> = mapValues { (propertyName, property) ->
    val type = if (property.isUnit()) {
        IRTree.Type.Builtin.UNIT
    } else {
        property.toIr(
            parentName = name,
            propertyName,
            irTypes,
        )
    }

    if (property.hasNoRef) {
        addToIr(type, irTypes)
    }

    val required = required

    IRTree.Member(
        type = type,
        nullable = when {
            required != null -> propertyName !in required
            else -> true
        },
        namespace = null,
        serialName = null,
        documentation = property.description,
        xmlType = null,
        requirements = listOfNotNull(
            if (property is Schema.STRING) {
                property.minLength?.let { IRTree.Member.Requirement.MinLength(it) }
            } else {
                null
            },
            if (property is Schema.STRING) {
                property.maxLength?.let {
                    IRTree.Member.Requirement.MaxLength(it)
                }
            } else {
                null
            },
        ),
        isOverride = false,
        deprecated = property.deprecated,
    )
}

private fun addToIr(type: IRTree.Type, irTypes: MutableMap<String, IRTree.Class>) {
    when (type) {
        is IRTree.Class -> {
            if (type.name !in irTypes) {
                irTypes[type.name] = type
            }
        }

        is IRTree.Type.Builtin -> return
        is IRTree.Type.LIST -> addToIr(type.list, irTypes)
        is IRTree.Type.MAP -> addToIr(type.value, irTypes)
    }
}

private val Schema.hasNoRef: Boolean
    get() = when (this) {
        is Schema.ARRAY -> items?.hasNoRef == true
        is Schema.BOOLEAN,
        is Schema.INT,
        is Schema.NUMBER,
        is Schema.STRING,
        -> false

        is Schema.OBJECT -> ref == null
    }
