package io.github.hfhbd.kfx.swagger

import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.getStatusCodes
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.swagger.Swagger.*
import kotlinx.serialization.json.*
import java.nio.file.Path
import kotlin.collections.get
import kotlin.collections.iterator
import kotlin.io.path.*

fun Path.createIr(
    swaggerTransformers: List<SwaggerTransformer>,
): IRTree {
    var swagger: Swagger = json.decodeFromString(readText())
    for (swaggerTransformer in swaggerTransformers) {
        swagger = swaggerTransformer(swagger)
    }
    val irTree = swagger.toIr()
    return irTree
}

internal fun Swagger.toIr(): IRTree {
    val irTypes = mutableMapOf<IRTree.ClassName, IRTree.Class>()
    for ((fullName, type) in definitions) {
        when (type.type) {
            Definition.Type.Object -> {
                val ir = type.objectToIr(null, fullName, irTypes, definitions)
                irTypes[IRTree.ClassName(ir.packageName, ir.name)] = ir
            }

            Definition.Type.Array,
            Definition.Type.Boolean,
            Definition.Type.Integer,
            Definition.Type.Number,
            Definition.Type.String,
            Definition.Type.Null, Definition.Type.File,
            -> continue
        }
    }

    val irOperations = mutableSetOf<IRTree.Operation>()
    for ((path, operations) in paths) {
        val head = operations.head
        if (head != null) {
            irOperations.add(generate(path, head, IRTree.Operation.HttpMethod.Head, irTypes, parameters, definitions))
        }
        val get = operations.get
        if (get != null) {
            irOperations.add(generate(path, get, IRTree.Operation.HttpMethod.Get, irTypes, parameters, definitions))
        }
        val post = operations.post
        if (post != null) {
            irOperations.add(generate(path, post, IRTree.Operation.HttpMethod.Post, irTypes, parameters, definitions))
        }
        val put = operations.put
        if (put != null) {
            irOperations.add(generate(path, put, IRTree.Operation.HttpMethod.Put, irTypes, parameters, definitions))
        }
        val patch = operations.patch
        if (patch != null) {
            irOperations.add(generate(path, patch, IRTree.Operation.HttpMethod.Patch, irTypes, parameters, definitions))
        }
        val delete = operations.delete
        if (delete != null) {
            irOperations.add(
                generate(
                    path,
                    delete,
                    IRTree.Operation.HttpMethod.Delete,
                    irTypes,
                    parameters,
                    definitions,
                ),
            )
        }
    }

    return IRTree(
        irTypes.values.toSet(),
        irOperations,
        auth = setOfNotNull(securityDefinitions?.toAuth()),
    )
}

private fun generate(
    path: String,
    operation: Swagger.Path,
    method: IRTree.Operation.HttpMethod,
    irTypes: MutableMap<IRTree.ClassName, IRTree.Class>,
    parameters: Map<String, Parameter>,
    definitions: Map<String, Definition>,
): IRTree.Operation {
    val statusCodes = operation.responses.keys.getStatusCodes()

    val name = operation.operationId ?: (
        method.toString() + path.split("/").joinToString("") {
            it.replaceFirstChar { it.uppercaseChar() }
        }.replaceFirstChar { it.uppercaseChar() }
        )

    return IRTree.Operation(
        packageName = "",
        name = name.replaceFirstChar { it.lowercaseChar() },
        documentation = operation.description,
        method = method,
        location = null,
        address = null,
        path = path.replace("{", "\${"),
        input = operation.parameters.mapNotNull {
            when (it.position) {
                Parameter.Position.Body -> it
                Parameter.Position.Query,
                Parameter.Position.Path,
                Parameter.Position.Header,
                null,
                -> null
            }
        }.singleOrNull()
            ?.toType(IRTree.ClassName("", name), irTypes, definitions),
        inputContentType = operation.consumes.firstOrNull()?.let {
            ContentType.fromString(it)
        },
        success = statusCodes.success?.toIntOrNull(),
        output = operation.responses[statusCodes.success]?.schema?.let {
            if (it.isUnit()) {
                IRTree.Type.Builtin.UNIT
            } else {
                it.toIr(
                    null,
                    name,
                    irTypes,
                    definitions = definitions,
                )
            }
        },
        outputContentType = operation.produces.firstOrNull()?.let {
            ContentType.fromString(it)
        },
        nullableOutput = if ("404" in operation.responses) 404 else null,
        fault = operation.responses[statusCodes.fault]?.schema?.let {
            val ref = it.ref
            val ir = (
                if (ref != null) {
                    irTypes.find(ref)
                } else {
                    it.toIr(
                        null,
                        null,
                        irTypes,
                        definitions,
                    )
                } as IRTree.NormalClass
                ).copy(
                isFault = true,
            )
            irTypes[IRTree.ClassName(ir.packageName, ir.name)] = ir
            ir
        },
        parameters = operation.parameters.mapNotNull {
            when (it.position) {
                Parameter.Position.Body -> null
                Parameter.Position.Query -> null
                Parameter.Position.Header -> null
                Parameter.Position.Path -> it.toParameter(
                    IRTree.ClassName("", name),
                    parameters,
                    irTypes,
                    definitions,
                ).second.copy(
                    nullable = false,
                )

                null -> it.toParameter(
                    IRTree.ClassName("", name),
                    parameters,
                    irTypes,
                    definitions,
                ).takeIf { it.first == Parameter.Position.Path }?.second?.copy(
                    nullable = false,
                )
            }
        },
        headers = operation.parameters.mapNotNull {
            val s = when (it.position) {
                Parameter.Position.Body -> null
                Parameter.Position.Path -> null
                Parameter.Position.Query -> null
                Parameter.Position.Header -> {
                    val a = it.toParameter(
                        IRTree.ClassName("", name),
                        parameters,
                        irTypes,
                        definitions,
                    ).second
                    a
                }

                null -> it.toParameter(
                    IRTree.ClassName("", name),
                    parameters,
                    irTypes,
                    definitions,
                ).takeIf { it.first == Parameter.Position.Header }?.second
            }
            s
        },
        queryParameters = operation.parameters.mapNotNull {
            when (it.position) {
                Parameter.Position.Body -> null
                Parameter.Position.Path -> null
                Parameter.Position.Header -> null
                Parameter.Position.Query -> it.toParameter(
                    IRTree.ClassName("", name),
                    parameters,
                    irTypes,
                    definitions,
                ).second.copy(
                    nullable = true,
                )

                null -> it.toParameter(
                    IRTree.ClassName("", name),
                    parameters,
                    irTypes,
                    definitions,
                ).takeIf { it.first == Parameter.Position.Query }?.second?.copy(
                    nullable = true,
                )
            }
        },
    )
}

private fun Map<String, SecurityDefinition>.toAuth(): IRTree.Auth? {
    val singleKey = keys.singleOrNull() ?: return null
    return when (val definition = this[singleKey]!!) {
        is SecurityDefinition.OAuth2 -> IRTree.Auth.OAuth2(
            flow = IRTree.Auth.OAuth2.Flow.Application,
            operation = IRTree.Operation(
                packageName = "",
                name = singleKey,
                documentation = definition.description,
                method = IRTree.Operation.HttpMethod.Post,
                path = definition.tokenUrl,
                parameters = listOf(
                    IRTree.Operation.Parameter(
                        "clientId",
                        null,
                        IRTree.Type.Builtin.STRING,
                        false,
                        null,
                        defaultValue = null,
                    ),
                    IRTree.Operation.Parameter(
                        "clientSecret",
                        null,
                        IRTree.Type.Builtin.STRING,
                        false,
                        null,
                        defaultValue = null,
                    ),
                ),
                address = null,
                queryParameters = emptyList(),
                fault = null,
                input = null,
                output = OAuth2Token,
                nullableOutput = null,
                location = null,
                success = 200,
                headers = emptyList(),
                inputContentType = null,
                outputContentType = ContentType.ApplicationJson,
            ),
            grantType = IRTree.Auth.OAuth2.GrantType.ClientCredentials,
        )

        is SecurityDefinition.ApiKey -> null
        is SecurityDefinition.BasicAuthentication -> {
            IRTree.Auth.Http(
                schema = IRTree.Auth.Http.Schema.Basic,
                name = singleKey,
                packageName = "",
                documentation = definition.description,
            )
        }
    }
}

private val OAuth2Token = IRTree.NormalClass(
    packageName = "io.github.hfhbd.kfx.oauth2",
    packageNameSuffix = "",
    name = "OAuth2Token",
    documentation = null,
    serialName = null,
    namespace = null,
    members = emptyMap(),
    isFault = false,
    allOf = null,
    discriminator = null,
)

private fun Definition.isUnit(): Boolean {
    val additionalProperties = additionalProperties
    return type == Definition.Type.Object &&
        properties.isEmpty() &&
        (additionalProperties == null || additionalProperties.isUnit()) &&
        ref == null && allOf.isEmpty()
}

private fun Parameter.toParameter(
    parentQName: IRTree.ClassName,
    parameters: Map<String, Parameter>,
    irTypes: MutableMap<IRTree.ClassName, IRTree.Class>,
    definitions: Map<String, Definition>,
): Pair<Parameter.Position?, IRTree.Operation.Parameter> = if (ref != null) {
    val name = ref!!.removePrefix("#/parameters/")
    val found = parameters[name]!!
    found.position to IRTree.Operation.Parameter(
        name = found.name!!,
        type = when (found.type!!) {
            Parameter.Types.String -> IRTree.Type.Builtin.STRING
            Parameter.Types.Int -> IRTree.Type.Builtin.INT
            Parameter.Types.Boolean -> IRTree.Type.Builtin.BOOLEAN
            Parameter.Types.Array -> IRTree.Type.LIST(
                list = found.items!!.toIr(
                    parentQName,
                    name,
                    irTypes,
                    definitions,
                ),
            )
        },
        serialName = null,
        nullable = when (found.required) {
            true -> false
            false -> true
            null -> true
        },
        documentation = found.description,
        defaultValue = defaultValue() ?: found.defaultValue(),
    )
} else {
    val required = required
    null to IRTree.Operation.Parameter(
        name = name!!,
        type = when (type!!) {
            Parameter.Types.String -> IRTree.Type.Builtin.STRING
            Parameter.Types.Int -> IRTree.Type.Builtin.INT
            Parameter.Types.Boolean -> IRTree.Type.Builtin.BOOLEAN
            Parameter.Types.Array -> IRTree.Type.LIST(
                list = items!!.toIr(
                    parentQName,
                    name,
                    irTypes,
                    definitions,
                ),
            )
        },
        nullable = if (required != null) {
            !required
        } else {
            true
        },
        documentation = description,
        serialName = null,
        defaultValue = defaultValue(),
    )
}

private fun Parameter.defaultValue(): IRTree.Literal? = when (val primitive = default?.jsonPrimitive) {
    null -> null
    else -> when (type!!) {
        Parameter.Types.String -> IRTree.Literal.STRING(primitive.content)
        Parameter.Types.Int -> IRTree.Literal.INT(primitive.int)
        Parameter.Types.Boolean -> IRTree.Literal.BOOLEAN(primitive.boolean)
        Parameter.Types.Array -> null
    }
}

private fun Parameter.toType(
    parentQName: IRTree.ClassName?,
    irTypes: MutableMap<IRTree.ClassName, IRTree.Class>,
    definitions: Map<String, Definition>,
): IRTree.Type = schema?.toIr(parentQName, null, irTypes, definitions) ?: irTypes.find(ref!!)

private fun Map<IRTree.ClassName, IRTree.Class>.findOrNull(id: String): IRTree.Class? {
    val id = id.removePrefix("#/definitions/").toIRTreeClassName()
    return this[id]
}

private fun Map<IRTree.ClassName, IRTree.Class>.find(id: String): IRTree.Class {
    return findOrNull(id) ?: error("$id not in $keys")
}

private fun Definition.toIr(
    parentQName: IRTree.ClassName?,
    name: String?,
    irTypes: MutableMap<IRTree.ClassName, IRTree.Class>,
    definitions: Map<String, Definition>,
): IRTree.Type = when (type) {
    Definition.Type.Array -> IRTree.Type.LIST(
        items?.toIr(parentQName, name, irTypes, definitions)
            ?: irTypes.find(ref!!),
    )

    Definition.Type.Boolean -> IRTree.Type.Builtin.BOOLEAN
    Definition.Type.Integer -> IRTree.Type.Builtin.INT
    Definition.Type.Number -> when (format) {
        "int32" -> IRTree.Type.Builtin.INT
        "int64" -> IRTree.Type.Builtin.LONG
        else -> error("Not supported $this")
    }

    Definition.Type.Object -> objectToIr(parentQName, name, irTypes, definitions)

    Definition.Type.String -> stringToIr(parentQName, name, irTypes)
    Definition.Type.File -> IRTree.Type.Builtin.FILE
    Definition.Type.Null -> error("Not supported $this")
}

private fun Definition.stringToIr(
    parentQName: IRTree.ClassName?,
    name: String?,
    irTypes: MutableMap<IRTree.ClassName, IRTree.Class>,
): IRTree.Type = if (enum.isNotEmpty()) {
    val qname = toIRTreeClassName(parentQName, name)
    val enum = IRTree.Enum(
        name = qname.name,
        packageName = qname.packageName,
        packageNameSuffix = "",
        documentation = description,
        values = enum.map {
            IRTree.Enum.Value(it.lowercase().toCamelCase().replaceFirstChar { it.uppercaseChar() }, null, it)
        },
    )
    irTypes[qname] = enum
    enum
} else {
    IRTree.Type.Builtin.STRING
}

private fun String.toCamelCase(): String = "_[a-zA-Z]".toRegex().replace(this) {
    it.value.replace("_", "").uppercase()
}

private fun String.toIRTreeClassName(): IRTree.ClassName {
    val (packageName, name) = removePrefix("#/definitions/").let { name ->
        if ("." in name) {
            val qName = name.split(".")
            qName.dropLast(1).joinToString(".") {
                it.lowercase()
            } to qName.last().replaceFirstChar { it.uppercaseChar() }
        } else {
            "" to name.replaceFirstChar { it.uppercaseChar() }
        }
    }
    return IRTree.ClassName(packageName, name)
}

private fun toIRTreeClassName(parentQName: IRTree.ClassName?, name: String?): IRTree.ClassName = parentQName?.copy(
    name = parentQName.name + name!!.replaceFirstChar {
        it.uppercaseChar()
    },
)
    ?: name!!.toIRTreeClassName()

private fun Definition.objectToIr(
    parentQName: IRTree.ClassName?,
    name: String?,
    irTypes: MutableMap<IRTree.ClassName, IRTree.Class>,
    definitions: Map<String, Definition>,
): IRTree.NormalClass {
    val qname = ref?.toIRTreeClassName() ?: toIRTreeClassName(parentQName, name)
    val members = buildMap {
        var sealedDiscriminator: String? = null
        for (allOf in allOf) {
            val allOfRef = allOf.ref
            if (allOfRef != null) {
                val allOfClass =
                    irTypes.findOrNull(allOfRef) as IRTree.NormalClass?
                        ?: definitions[allOfRef.removePrefix("#/definitions/")]!!.objectToIr(
                            null,
                            allOfRef,
                            irTypes,
                            mapOf(),
                        ).also {
                            irTypes[IRTree.ClassName(it.packageName, it.name)] = it
                        }

                sealedDiscriminator = allOfClass.discriminator

                for ((memberName, member) in allOfClass.members) {
                    if (memberName != allOfClass.discriminator) {
                        put(memberName, member.copy(isOverride = true))
                    }
                }
            } else {
                for ((propertyName, property) in allOf.properties) {
                    if (propertyName != sealedDiscriminator) {
                        convertProperty(propertyName, property, allOf.required, qname, irTypes, definitions)
                    }
                }
            }
        }
        for ((propertyName, property) in properties) {
            convertProperty(propertyName, property, required, qname, irTypes, definitions)
        }
    }
    val type = IRTree.NormalClass(
        packageName = qname.packageName,
        packageNameSuffix = "",
        name = qname.name,
        serialName = null,
        namespace = null,
        members = members,
        documentation = description,
        isFault = false,
        allOf = allOf.mapNotNull {
            it.ref?.toIRTreeClassName()
        }.singleOrNull(),
        discriminator = discriminator,
    )
    if (ref == null) {
        irTypes[qname] = type
    }
    return type
}

private fun MutableMap<String, IRTree.Member>.convertProperty(
    propertyName: String,
    property: Definition,
    required: List<String>,
    qname: IRTree.ClassName,
    irTypes: MutableMap<IRTree.ClassName, IRTree.Class>,
    definitions: Map<String, Definition>,
) {
    val type = if (property.isUnit()) {
        IRTree.Type.Builtin.UNIT
    } else {
        property.toIr(
            qname,
            propertyName,
            irTypes,
            definitions,
        )
    }

    val member = IRTree.Member(
        type = type,
        nullable = when {
            type is IRTree.Type.LIST -> false
            else -> propertyName !in required
        },
        namespace = null,
        serialName = null,
        documentation = property.description,
        xmlType = null,
        requirements = emptyList(),
        isOverride = false,
    )

    val existing = this[propertyName]
    if (existing != null) {
        put(
            propertyName,
            member.copy(
                isOverride = true,
                type = existing.type,
            ),
        )
    } else {
        put(propertyName, member)
    }
}
