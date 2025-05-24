package io.github.hfhbd.kfx.openapi

import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.getStatusCodes
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.IRTree.Literal.*
import io.github.hfhbd.kfx.openapi.OpenApi.Components.*
import kotlinx.datetime.*
import kotlinx.datetime.Instant
import java.nio.file.*
import java.util.*
import kotlin.collections.get
import kotlin.collections.iterator
import kotlin.io.path.*
import kotlin.text.removePrefix
import kotlin.time.Duration
import kotlin.uuid.Uuid

fun Path.createIr(
    openapiTransformers: List<OpenApiTransformer>,
): IRTree {
    var openapi: OpenApi = json.decodeFromString(readText())
    for (openapiTransformer in openapiTransformers) {
        openapi = openapiTransformer(openapi)
    }
    val irTree = openapi.toIr()
    return irTree
}

internal fun OpenApi.toIr(): IRTree {
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
                val irType = type.toIr(null, name, irTypes)
                when (irType) {
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
                    components.responses,
                    irTypes,
                    IRTree.Operation.HttpMethod.Delete,
                ),
            )
        }
    }

    val irTree = IRTree(
        irTypes.values.toSet(),
        irOperations,
        auth = components.securitySchemes.flatMapTo(mutableSetOf()) {
            toAuth(it.key, it.value)
        },
    )

    return handleSealedClassMapping(irTree, this)
}

private fun OpenApi.Operation.toIr(
    path: String,
    pathParameters: List<OpenApi.Parameter>,
    componentParameters: Map<String, OpenApi.Parameter>,
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

    val name = id.toCamelCase()
    return IRTree.Operation(
        packageName = "",
        name = name.replaceFirstChar { it.lowercase() },
        documentation = doc,
        method = method,
        location = null,
        address = null,
        path = path.replace("{", "\${"),
        input = requestBody?.content?.values?.first()?.schema?.takeUnless { it.isUnit() }?.toIr(
            parentName = name,
            name = name,
            irTypes,
        ),
        inputContentType = requestBody?.content?.entries?.first()?.key?.let { ContentType.fromString(it) },
        output = responses[statusCodes.success]?.toIr(
            name,
            componentsResponses,
            irTypes,
        ),
        outputContentType = responses[statusCodes.success]?.content?.entries?.firstOrNull()?.key?.let {
            ContentType.fromString(it)
        },
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
        success = statusCodes.success?.toIntOrNull(),
        nullableOutput = if ("404" in responses.keys) 404 else null,
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
                            ),
                            IRTree.Operation.Parameter(
                                name = "clientSecret",
                                serialName = null,
                                type = IRTree.Type.Builtin.STRING,
                                nullable = false,
                                documentation = null,
                                defaultValue = null,
                            ),
                        ),
                        address = null,
                        queryParameters = emptyList(),
                        fault = null,
                        input = null,
                        inputContentType = null,
                        output = OAuth2Token,
                        outputContentType = ContentType.ApplicationJson,
                        location = null,
                        success = 200,
                        nullableOutput = null,
                        headers = emptyList(),
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
        )
    } else {
        return null to IRTree.Operation.Parameter(
            name = name!!,
            type = schema!!.toIr(name, name, irTypes),
            nullable = !required,
            documentation = description,
            serialName = null,
            defaultValue = schema!!.toIrDefault(),
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
        Schema.STRING.Format.Uuid -> default?.let { IRTree.Literal.UUID(Uuid.parse(it)) }
        else -> default?.let { STRING(it) }
    }

    is Schema.INT -> when (format) {
        Schema.INT.Format.Int32 -> default?.let { INT(it.toInt()) }
        Schema.INT.Format.Int64 -> default?.let { LONG(it) }
    }

    is Schema.BOOLEAN -> default?.let { BOOLEAN(it) }
    is Schema.OBJECT -> null
    is Schema.ARRAY -> null
    is Schema.NUMBER -> when (format) {
        Schema.NUMBER.Format.Float -> default?.let { FLOAT(it.toFloat()) }
        Schema.NUMBER.Format.Double -> default?.let { DOUBLE(it) }
    }
}

private fun Map<String, IRTree.Class>.findOrNull(id: String): IRTree.Class? {
    val id = id.removePrefix("#/components/schemas/")
    return this[id]
}

private fun Map<String, IRTree.Class>.find(id: String): IRTree.Class {
    return findOrNull(id) ?: error("$id not in $keys")
}

private fun Schema.toIr(
    parentName: String?,
    name: String?,
    irTypes: MutableMap<String, IRTree.Class>,
): IRTree.Type = when (this) {
    is Schema.ARRAY -> toIr(parentName!!, irTypes)
    is Schema.BOOLEAN -> toIr()
    is Schema.INT -> toIr()
    is Schema.NUMBER -> toIr()
    is Schema.OBJECT -> toIr(name, irTypes)
    is Schema.STRING -> toIr(parentName, name, irTypes)
}

private fun Schema.BOOLEAN.toIr() = IRTree.Type.Builtin.BOOLEAN

private fun Schema.ARRAY.toIr(parentName: String, irTypes: MutableMap<String, IRTree.Class>) = IRTree.Type.LIST(
    items?.toIr(null, parentName + "Items", irTypes) ?: irTypes.find(ref!!),
)

private fun Schema.INT.toIr() = when (format) {
    Schema.INT.Format.Int32 -> IRTree.Type.Builtin.INT
    Schema.INT.Format.Int64 -> IRTree.Type.Builtin.LONG
}

private fun Schema.NUMBER.toIr() = when (format) {
    Schema.NUMBER.Format.Double -> IRTree.Type.Builtin.DOUBLE
    Schema.NUMBER.Format.Float -> IRTree.Type.Builtin.FLOAT
}

private fun Schema.STRING.toIr(
    parentName: String?,
    name: String?,
    irTypes: MutableMap<String, IRTree.Class>,
): IRTree.Type = if (enum.isNotEmpty()) {
    val name = (parentName?.toCamelCase() ?: "") + name!!.toCamelCase()
    val enum = IRTree.Enum(
        name = name,
        packageName = "",
        packageNameSuffix = "",
        documentation = description,
        values = enum.filterNotNull().map {
            IRTree.Enum.Value(it.lowercase().toCamelCase().replaceFirstChar { it.uppercaseChar() }, null, it)
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

private fun String.toCamelCase(): String = "[_\\-/][a-zA-Z]".toRegex().replace(this) {
    it.value.replace("_", "").replace("-", "").replace("/", "").uppercase(Locale.ROOT)
}.replaceFirstChar {
    it.uppercaseChar()
}

private fun Schema.isUnit(): Boolean = this is Schema.OBJECT && ref == null && properties.isEmpty() && additionalPropertiesSchema == null

private fun Schema.OBJECT.asClassName(name: String?): IRTree.ClassName {
    return (ref?.removePrefix("#/components/schemas/") ?: name!!).let { name ->
        if ("." in name) {
            val qName = name.split(".")
            IRTree.ClassName(
                qName.dropLast(1).joinToString(".") {
                    it.lowercase()
                },
                qName.last().replaceFirstChar { it.uppercaseChar() },
            )
        } else {
            IRTree.ClassName("", name.toCamelCase())
        }
    }
}

private fun Schema.OBJECT.toIr(
    name: String?,
    irTypes: MutableMap<String, IRTree.Class>,
): IRTree.Type {
    val resolvedRef = asClassName(name)
    val discriminator = discriminator?.propertyName
    if (additionalPropertiesSchema != null) {
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
): Map<String, IRTree.Member> {
    return mapValues { (propertyName, property) ->
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
                type is IRTree.Type.LIST -> false
                required != null -> propertyName !in required
                else -> true
            },
            namespace = null,
            serialName = null,
            documentation = property.description,
            xmlType = null,
            requirements = emptyList(),
            isOverride = false,
        )
    }
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
