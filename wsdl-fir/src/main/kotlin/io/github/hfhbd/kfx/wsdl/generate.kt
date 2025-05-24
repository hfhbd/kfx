package io.github.hfhbd.kfx.wsdl

import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.ir.IRTree
import nl.adaptivity.xmlutil.core.*
import java.nio.file.*
import kotlin.collections.iterator
import kotlin.io.path.*

fun Path.createIr(
    wsdlTransformerFactories: List<WsdlTransformerFactory>,
    import: (String) -> Path,
): IRTree {
    val xml = xml(
        wsdlTransformerFactories.map { it.serializerModule() },
    )

    val reader = KtXmlReader(reader())
    var wsdl: WSDL = xml.decodeFromReader(reader)
    val firTransformers = wsdlTransformerFactories.map { it.create() }
    for (firTransformer in firTransformers) {
        wsdl = firTransformer(wsdl)
    }
    return wsdl.toIr({ prefix ->
        reader.getNamespaceURI(prefix)
    }) {
        var schema = xml.decodeFromString(Schema.serializer(), import(it).readText())
        for (firTransformer in firTransformers) {
            schema = firTransformer(schema)
        }
        schema
    }
}

private val String.packageName: String
    get() {
        val parts = removePrefix("urn:").removePrefix("http://").split("/")
        val host = parts[0].split(".").reversed()
        return (host + parts.drop(1)).joinToString(".") {
            val s = it.lowercase()
                .replace("-", "_")
                .replace(".", "")
            if (s.toIntOrNull() != null) {
                "v$s"
            } else {
                s
            }
        }
    }

private sealed interface Classes {
    data class TypeAlias(val actual: IRTree.ClassName) : Classes
    data class ActualClass(val forClass: IRTree.Type) : Classes
}

fun WSDL.toIr(
    getNS: (String) -> String?,
    import: (String) -> Schema,
): IRTree {
    val irTypes = mutableMapOf<IRTree.ClassName, Classes>()
    for (type in types) {
        for (schema in type.schemas) {
            toIr(schema, false, irTypes, import)
        }
    }
    for (type in types) {
        for (schema in type.schemas) {
            toIr(schema, true, irTypes, import)
        }
    }

    val namespaces = allNamespaces()

    for (message in messages) {
        val (ns, name) = message.part.element.split(":")
        val typeAlias = IRTree.ClassName(targetNamespace.packageName, message.name)
        val namespace = namespaces[ns] ?: getNS(ns)
        val resolved = IRTree.ClassName(namespace?.packageName ?: targetNamespace.packageName, name)
        if (resolved != typeAlias) {
            irTypes[typeAlias] = Classes.TypeAlias(resolved)
        }
    }

    val faults = portType.operations.map {
        it.fault
    }.mapNotNull {
        it?.resolve(this)
    }.toSet()

    val operations = mutableSetOf<IRTree.Operation>()
    for (operation in portType.operations) {
        operations.add(
            IRTree.Operation(
                packageName = targetNamespace.packageName,
                name = operation.name.replaceFirstChar { it.lowercaseChar() },
                documentation = operation.documentation?.trimDocumentation(),
                location = service.port.address.location,
                address = "$targetNamespace/${portType.name}/${operation.name}",
                input = irTypes.find(operation.input.resolve(this)) as IRTree.NormalClass,
                output = irTypes.find(operation.output.resolve(this)) as IRTree.NormalClass,
                nullableOutput = null,
                fault = operation.fault?.resolve(this)?.let { irTypes.find(it) as IRTree.NormalClass? },
                path = null,
                method = IRTree.Operation.HttpMethod.Post,
                parameters = emptyList(),
                queryParameters = emptyList(),
                success = 200,
                headers = emptyList(),
                inputContentType = ContentType.ApplicationSoapXml,
                outputContentType = ContentType.ApplicationSoapXml,
            ),
        )
    }

    val irTree = IRTree(
        irTypes.resolveMembers(
            faults,
        ),
        operations,
        auth = emptySet(),
    )
    return irTree
}

private fun toIr(
    schema: Schema,
    includeMembers: Boolean,
    irTypes: MutableMap<IRTree.ClassName, Classes>,
    import: (String) -> Schema,
) {
    for (import in schema.imports) {
        val schemaLocation = import.schemaLocation
        if (schemaLocation != null) {
            toIr(
                import(schemaLocation),
                includeMembers,
                irTypes,
            )
        }
    }

    toIr(
        schema,
        includeMembers,
        irTypes,
    )
}

private fun toIr(
    schema: Schema,
    includeMembers: Boolean,
    irTypes: MutableMap<IRTree.ClassName, Classes>,
) {
    for (simpleType in schema.simpleType) {
        if (simpleType.restriction.enumeration.isNotEmpty()) {
            val packageName = schema.targetNamespace.packageName
            val simpleTypeName = simpleType.name
            irTypes[IRTree.ClassName(packageName, simpleTypeName!!)] = Classes.ActualClass(
                IRTree.Enum(
                    packageName = schema.targetNamespace.packageName,
                    packageNameSuffix = "",
                    name = simpleTypeName,
                    values = simpleType.restriction.enumeration.map {
                        val v = it.value.replace(".", "_")
                        val name = if (v.first().digitToIntOrNull() != null) {
                            "v$v"
                        } else {
                            v
                        }
                        IRTree.Enum.Value(name, null, null)
                    },
                    documentation = null,
                ),
            )
        } else {
            val typeAlias = IRTree.ClassName(schema.targetNamespace.packageName, simpleType.name!!.remove())
            val resolved = simpleType.toBuiltin()!!
            irTypes[typeAlias] = Classes.ActualClass(resolved)
        }
    }
    for (element in schema.elements) {
        val elementType = element.type
        if (elementType != null) {
            val ns: String
            val type: String
            if (":" in elementType) {
                ns = elementType.split(":").component1()
                type = elementType.split(":").component2()
            } else {
                ns = schema.targetNamespace
                type = elementType
            }

            val typeAlias = IRTree.ClassName(schema.targetNamespace.packageName, element.name!!)
            val resolved = IRTree.ClassName(schema.namespace(ns), type.remove(suffix = true))
            if (resolved != typeAlias) {
                irTypes[typeAlias] = Classes.TypeAlias(resolved)
            }
        } else {
            val elementName = element.name!!
            val packageName = schema.targetNamespace.packageName
            val qname = IRTree.ClassName(packageName, elementName)
            irTypes[qname] = Classes.ActualClass(
                IRTree.NormalClass(
                    packageName = schema.targetNamespace.packageName,
                    packageNameSuffix = "",
                    name = elementName.remove(),
                    serialName = elementName,
                    namespace = schema.targetNamespace,
                    documentation = element.annotation?.documentation(),
                    members = element.complexType?.sequence?.elements?.map {
                        when (it) {
                            is Choice -> it.element
                            is Element -> it
                        }
                    }?.mapToIr(qname, schema, irTypes) ?: emptyMap(),
                    isFault = false,
                    allOf = null,
                    discriminator = null,
                ),
            )
        }
    }

    for (complexType in schema.complexTypes) {
        val complexTypeName = complexType.name
        val qName = IRTree.ClassName(schema.targetNamespace.packageName, complexTypeName!!.remove())
        val sequence = complexType.sequence
        if (sequence != null && sequence.elements.size == 1 && (sequence.elements[0] as Element).name == null) {
            val element = sequence.elements[0] as Element
            val (ns, name) = element.ref!!.split(":")
            val typeAlias = IRTree.ClassName(schema.targetNamespace.packageName, complexTypeName.remove())
            if (element.maxOccurs == "unbounded") {
                irTypes[typeAlias] = Classes.ActualClass(
                    IRTree.NormalClass(
                        packageName = typeAlias.packageName,
                        packageNameSuffix = "",
                        name = typeAlias.name.remove(),
                        namespace = schema.targetNamespace,
                        serialName = complexType.name,
                        members = if (includeMembers) {
                            sequence.elements.map {
                                when (it) {
                                    is Choice -> it.element
                                    is Element -> it
                                }
                            }.mapToIr(typeAlias, schema, irTypes)
                        } else {
                            emptyMap()
                        },
                        documentation = complexType.annotation?.documentation(),
                        isFault = false,
                        allOf = null,
                        discriminator = null,
                    ),
                )
            } else {
                val resolved = IRTree.ClassName(schema.namespace(ns), name)
                if (resolved != typeAlias) {
                    irTypes[typeAlias] = Classes.TypeAlias(resolved)
                }
            }
        } else if (complexType.simpleContent != null) {
            irTypes[qName] = Classes.ActualClass(
                IRTree.NormalClass(
                    packageName = schema.targetNamespace.packageName,
                    packageNameSuffix = "",
                    name = complexTypeName.remove(),
                    namespace = schema.targetNamespace,
                    serialName = complexType.name,
                    members = if (includeMembers) {
                        buildMap {
                            put(
                                "value",
                                IRTree.Member(
                                    type = complexType.simpleContent!!.extension.base.toBuiltin()!!,
                                    nullable = false,
                                    serialName = "",
                                    namespace = "",
                                    documentation = "",
                                    xmlType = IRTree.XmlType.Value,
                                    requirements = emptyList(),
                                    isOverride = false,
                                ),
                            )
                            for (it in complexType.simpleContent!!.extension.attributes) {
                                val s = it.mapToIr(schema, irTypes)
                                put(s.first, s.second)
                            }
                        }
                    } else {
                        emptyMap()
                    },
                    documentation = complexType.annotation?.documentation(),
                    isFault = false,
                    allOf = null,
                    discriminator = null,
                ),
            )
        } else {
            irTypes[qName] = Classes.ActualClass(
                IRTree.NormalClass(
                    packageName = schema.targetNamespace.packageName,
                    packageNameSuffix = "",
                    name = complexTypeName.remove(),
                    namespace = schema.targetNamespace,
                    serialName = complexType.name,
                    members = if (includeMembers) {
                        sequence?.elements?.map {
                            when (it) {
                                is Choice -> it.element
                                is Element -> it
                            }
                        }?.mapToIr(qName, schema, irTypes) ?: emptyMap()
                    } else {
                        emptyMap()
                    },
                    documentation = complexType.annotation?.documentation(),
                    isFault = false,
                    allOf = null,
                    discriminator = null,
                ),
            )
        }
    }
}

private fun Annotation.documentation(): String? {
    return documentation?.values?.mapNotNull {
        (it as? String)?.trimDocumentation() ?: return@mapNotNull null
    }?.joinToString("")?.trim()
}

private fun String.trimDocumentation(): String {
    val docs = split("\n")
    return docs.joinToString(" ") {
        it.trim()
    }.trim()
}

private fun Schema.namespace(ns: String): String {
    return (annotation?.appInfo?.appInfo?.filterIsInstance<NS>())?.singleOrNull { it.prefix == ns }?.uri?.packageName
        ?: targetNamespace.packageName
}

private fun Map<IRTree.ClassName, Classes>.resolveMembers(faults: Set<IRTree.ClassName>): Set<IRTree.Class> = buildSet {
    val resolvedFaults = faults.mapTo(mutableSetOf()) {
        val found = find(it) as IRTree.NormalClass
        IRTree.ClassName(found.packageName, found.name)
    }
    for ((_, classes) in this@resolveMembers) {
        when (classes) {
            is Classes.TypeAlias -> continue
            is Classes.ActualClass -> when (val forClass = classes.forClass) {
                is IRTree.Type.Builtin -> continue
                is IRTree.Type.LIST -> continue
                is IRTree.Type.MAP -> continue
                is IRTree.Enum -> add(forClass)
                is IRTree.NormalClass -> {
                    val isFault = IRTree.ClassName(forClass.packageName, forClass.name) in resolvedFaults
                    add(
                        forClass.copy(
                            members = forClass.members.mapValues { (_, it) ->
                                it.resolve(this@resolveMembers)
                            },
                            isFault = isFault,
                        ),
                    )
                }
            }
        }
    }
}

private fun IRTree.Member.resolve(from: Map<IRTree.ClassName, Classes>): IRTree.Member {
    val type = type
    if (type !is IRTree.NormalClass) {
        return this
    }
    val qName = IRTree.ClassName(type.packageName, type.name)
    val resolved = from.find(qName)
    return copy(type = resolved)
}

private fun List<Element>.mapToIr(
    prefix: IRTree.ClassName,
    schema: Schema,
    topLevel: MutableMap<IRTree.ClassName, Classes>,
): Map<String, IRTree.Member> {
    return associate {
        val extension = it.complexType?.simpleContent?.extension
        val ref = (it.type ?: it.ref ?: it.name!!).split(":")
        val ns: String?
        fun createCustomWrapper(type: IRTree.Type): IRTree.NormalClass {
            val qname = IRTree.ClassName(schema.targetNamespace.packageName, prefix.name + ref[0])
            val classe = IRTree.NormalClass(
                packageName = qname.packageName,
                packageNameSuffix = "",
                name = qname.name,
                serialName = ref[0],
                namespace = schema.targetNamespace,
                isFault = false,
                members = buildMap {
                    val complexType = it.complexType
                    if (complexType != null) {
                        val elements = complexType.sequence?.elements?.map {
                            when (it) {
                                is Choice -> it.element
                                is Element -> it
                            }
                        }?.mapToIr(qname, schema, topLevel)
                        if (elements != null) {
                            putAll(elements)
                        }

                        val simpleType = complexType.simpleContent
                        if (simpleType != null) {
                            for (attribute in simpleType.extension.attributes) {
                                val s = attribute.mapToIr(schema, topLevel)
                                put(s.first, s.second)
                            }
                        }
                    }
                    put(
                        "value",
                        IRTree.Member(
                            type = type,
                            nullable = false,
                            serialName = null,
                            namespace = null,
                            documentation = null,
                            xmlType = IRTree.XmlType.Value,
                            requirements = emptyList(),
                            isOverride = false,
                        ),
                    )
                },
                documentation = it.annotation?.documentation(),
                allOf = null,
                discriminator = null,
            )
            if (qname !in topLevel) {
                topLevel[qname] = Classes.ActualClass(classe)
            }
            return classe
        }

        val type: IRTree.Type = if (ref.size == 2) {
            ns = ref[0]
            val name = ref[1]
            if (ns == "xsd" || ns == "xs") {
                ":$name".toBuiltin()!!.let {
                    if (extension != null && extension.attributes.isNotEmpty()) {
                        createCustomWrapper(it)
                    } else {
                        it
                    }
                }
            } else {
                val namespace = schema.namespace(ns)
                val qname = IRTree.ClassName(namespace, name.remove())
                topLevel.find(qname).let {
                    if (extension != null && extension.attributes.isNotEmpty()) {
                        createCustomWrapper(it)
                    } else {
                        it
                    }
                }
            }
        } else if (it.simpleType != null) {
            ns = null
            it.simpleType!!.resolve(schema, topLevel).let {
                if (extension != null && extension.attributes.isNotEmpty()) {
                    createCustomWrapper(it)
                } else {
                    it
                }
            }
        } else if (ref.size == 1 && topLevel.findOrNull(
                IRTree.ClassName(
                    schema.targetNamespace.packageName,
                    ref[0].remove(),
                ),
            ) != null
        ) {
            ns = null
            topLevel.find(IRTree.ClassName(schema.targetNamespace.packageName, ref[0].remove())).let {
                if (extension != null && extension.attributes.isNotEmpty()) {
                    createCustomWrapper(it)
                } else {
                    it
                }
            }
        } else {
            ns = null
            val qname = IRTree.ClassName(schema.targetNamespace.packageName, ref[0])
            val classe = IRTree.NormalClass(
                packageName = qname.packageName,
                packageNameSuffix = "",
                name = qname.name,
                serialName = ref[0],
                namespace = schema.targetNamespace,
                isFault = false,
                members = it.complexType?.sequence?.elements?.map {
                    when (it) {
                        is Choice -> it.element
                        is Element -> it
                    }
                }?.mapToIr(qname, schema, topLevel) ?: emptyMap(),
                documentation = it.annotation?.documentation(),
                allOf = null,
                discriminator = null,
            )
            if (qname !in topLevel) {
                topLevel[qname] = Classes.ActualClass(classe)
            }
            classe
        }

        val elementName = (it.name ?: it.ref!!.split(":")[1])

        elementName.replaceFirstChar { it.lowercaseChar() } to
            IRTree.Member(
                type = if (it.maxOccurs == "unbounded") {
                    IRTree.Type.LIST(type)
                } else {
                    type
                },
                nullable = it.nillable == true || it.minOccurs == "0",
                serialName = elementName,
                namespace = if (it.ref == null) {
                    schema.targetNamespace
                } else {
                    (schema.annotation?.appInfo!!.appInfo.filterIsInstance<NS>()).single { it.prefix == ns }.uri
                },
                documentation = it.annotation?.documentation(),
                xmlType = IRTree.XmlType.Element,
                requirements = listOf(),
                isOverride = false,
            )
    }
}

@JvmName("mapToIrAttributes")
private fun Attribute.mapToIr(schema: Schema, topLevel: Map<IRTree.ClassName, Classes>): Pair<String, IRTree.Member> {
    val (ns, name) = requireNotNull(type) {
        "$this $schema"
    }.split(":")
    val type = if (ns == "xsd" || ns == "ns" || ns == "xs") {
        ":$name".toBuiltin()!!
    } else {
        val namespace = schema.namespace(ns)
        val qname = IRTree.ClassName(namespace, name.remove())
        topLevel.find(qname)
    }

    return this.name.replaceFirstChar { it.lowercaseChar() }.remove() to IRTree.Member(
        type = type,
        nullable = use == null || use == "optional",
        serialName = this.name,
        namespace = schema.targetNamespace,
        documentation = annotation?.documentation(),
        xmlType = IRTree.XmlType.Attribute,
        requirements = listOf(),
        isOverride = false,
    )
}

private fun Map<IRTree.ClassName, Classes>.find(qname: IRTree.ClassName): IRTree.Type =
    findOrNull(qname)
        ?: error("$qname not found in $keys")

private fun Map<IRTree.ClassName, Classes>.findOrNull(qname: IRTree.ClassName): IRTree.Type? = when (
    val value = get(
        qname,
    )
) {
    is Classes.TypeAlias -> find(value.actual)
    is Classes.ActualClass -> value.forClass
    null -> null
}

private fun WSDL.allNamespaces(): Map<String, String> = types.flatMap {
    val s = it.schemas.flatMap {
        (it.annotation?.appInfo?.appInfo?.filterIsInstance<NS>())?.map {
            it.prefix to it.uri
        } ?: emptyList()
    }
    s
}.associate { it }

private fun Type.resolve(definitions: WSDL): IRTree.ClassName {
    val (namespace, name) = message.split(":")

    val found = definitions.allNamespaces()[namespace]

    return if (found != null) {
        IRTree.ClassName(found.packageName, name)
    } else {
        IRTree.ClassName(definitions.targetNamespace.packageName, name)
    }
}

private fun SimpleType.toBuiltin(): IRTree.Type.Builtin? = restriction.base.toBuiltin()

private fun SimpleType.resolve(schema: Schema, irTypes: Map<IRTree.ClassName, Classes>): IRTree.Type = toBuiltin()
    ?: irTypes.find(IRTree.ClassName(schema.targetNamespace.packageName, restriction.base.remove()))

private fun String.toBuiltin(): IRTree.Type.Builtin? = when {
    endsWith(":string", ignoreCase = true) -> IRTree.Type.Builtin.STRING
    endsWith(":anyURI") -> IRTree.Type.Builtin.STRING
    endsWith(":normalizedString") -> IRTree.Type.Builtin.STRING
    endsWith(":dateTime") -> IRTree.Type.DateType.INSTANT
    endsWith(":date") -> IRTree.Type.DateType.DATE
    endsWith(":base64Binary") -> IRTree.Type.Builtin.STRING
    endsWith(":boolean") -> IRTree.Type.Builtin.BOOLEAN
    endsWith(":integer") || endsWith(":int") -> IRTree.Type.Builtin.INT
    endsWith(":decimal") -> IRTree.Type.Builtin.DOUBLE
    else -> null
}

private fun String.remove(suffix: Boolean = true): String {
    val s = split(":").let {
        if (it.size == 1) {
            it[0]
        } else {
            it[1]
        }
    }
    return if (suffix) s.removeSuffix("Type") else s
}
