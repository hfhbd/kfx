package io.github.hfhbd.kfx.xsd

import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.toCodeGen
import nl.adaptivity.xmlutil.core.*
import java.io.InputStream
import java.nio.file.Path
import java.util.ServiceLoader

fun generate(
    xsdFile: InputStream,
    import: (String) -> InputStream,
    outputDirectory: Path,
    xsdTransformerFactories: Iterable<XsdTransformerFactory> = ServiceLoader.load(XsdTransformerFactory::class.java),
    transformerFactories: Iterable<IrTransformer> = ServiceLoader.load(IrTransformer::class.java),
    codeGenCreator: CodeGenCreator = ServiceLoader.load(CodeGenCreator::class.java).single(),
    codeGenTransformer: Iterable<CodeGenTransformer> = ServiceLoader.load(CodeGenTransformer::class.java),
    codeGenerators: Iterable<CodeGenerator> = ServiceLoader.load(CodeGenerator::class.java),
) {
    val irTree = xsdFile.createIr(
        xsdTransformerFactories,
        import,
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
    xsdTransformerFactories: Iterable<XsdTransformerFactory>,
    import: (String) -> InputStream,
): IRTree {
    val xml = xml(
        xsdTransformerFactories.map { it.serializerModule() },
    )

    val reader = KtXmlReader(this)
    val schema = xml.decodeFromReader(Schema.serializer(), reader)
    val irTree = schema.toIr(xsdTransformerFactories.map { it.create() }) {
        xml.decodeFromReader(Schema.serializer(), KtXmlReader(import(it)))
    }
    return irTree
}

private val String.packageName: String
    get() {
        val parts = removePrefix("urn:").removePrefix("http://").removePrefix("https://").split("/")
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

sealed interface Classes {
    data class TypeAlias(val actual: IRTree.ClassName) : Classes
    data class ActualClass(val forClass: IRTree.Type) : Classes
}

private fun Schema.toIr(
    xsdTransformers: Collection<XsdTransformer>,
    import: (String) -> Schema,
): IRTree {
    val irTypes = mutableMapOf<IRTree.ClassName, Classes>()

    toIr(
        xsdTransformers = xsdTransformers,
        irTypes = irTypes,
        import = import,
    )

    val irTree = IRTree(
        classes = irTypes.resolveMembers(),
        operations = emptySet(),
        auth = emptySet(),
    )
    return irTree
}

fun Schema.toIr(
    xsdTransformers: Collection<XsdTransformer>,
    irTypes: MutableMap<IRTree.ClassName, Classes>,
    import: (String) -> Schema,
) {
    toIr(
        xsdTransformers = emptyList(),
        includeMembers = false,
        irTypes = irTypes,
        import = import,
    )

    toIr(
        xsdTransformers = xsdTransformers,
        includeMembers = true,
        irTypes = irTypes,
        import = import,
    )
}

private fun Schema.toIr(
    xsdTransformers: Collection<XsdTransformer>,
    includeMembers: Boolean,
    irTypes: MutableMap<IRTree.ClassName, Classes>,
    import: (String) -> Schema,
) {
    for (import in imports) {
        val schemaLocation = import.schemaLocation
        if (schemaLocation != null) {
            val importedSchema = import(schemaLocation)
            importedSchema.toIr(
                xsdTransformers,
                includeMembers,
                irTypes,
            )
        }
    }

    toIr(
        xsdTransformers,
        includeMembers,
        irTypes,
    )
}

private fun Schema.toIr(
    xsdTransformers: Collection<XsdTransformer>,
    includeMembers: Boolean,
    irTypes: MutableMap<IRTree.ClassName, Classes>,
) {
    for (simpleType in simpleType) {
        if (simpleType.restriction.enumeration.isNotEmpty()) {
            val packageName = targetNamespace.packageName
            val simpleTypeName = simpleType.name
            var irClass: IRTree.Class = IRTree.Enum(
                packageName = targetNamespace.packageName,
                packageNameSuffix = "",
                name = simpleTypeName!!,
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
                deprecated = false,
            )
            for (xsdTransformer in xsdTransformers) {
                irClass = xsdTransformer(simpleType, irClass)
            }
            irTypes[IRTree.ClassName(packageName, simpleTypeName)] = Classes.ActualClass(irClass)
        } else {
            val typeAlias = IRTree.ClassName(targetNamespace.packageName, simpleType.name!!.remove())
            val resolved = simpleType.toBuiltin()!!
            irTypes[typeAlias] = Classes.ActualClass(resolved)
        }
    }
    for (element in elements) {
        val elementType = element.type
        if (elementType != null) {
            val type = if (":" in elementType) {
                elementType.split(":").component2()
            } else {
                elementType
            }

            val typeAlias = IRTree.ClassName(targetNamespace.packageName, element.name!!)
            val resolved = IRTree.ClassName(namespace(), type.remove(suffix = true))
            if (resolved != typeAlias) {
                irTypes[typeAlias] = Classes.TypeAlias(resolved)
            }
        } else {
            val elementName = element.name!!
            val packageName = targetNamespace.packageName
            val qname = IRTree.ClassName(packageName, elementName)
            var irClass: IRTree.Class = IRTree.NormalClass(
                packageName = targetNamespace.packageName,
                packageNameSuffix = "",
                name = elementName.remove(),
                serialName = elementName,
                namespace = targetNamespace,
                documentation = element.annotation?.documentation(),
                members = element.complexType?.sequence?.elements?.map {
                    when (it) {
                        is Choice -> it.element
                        is Element -> it
                    }
                }?.mapToIr(qname, this, xsdTransformers, irTypes) ?: emptyMap(),
                isFault = false,
                allOf = null,
                discriminator = null,
                deprecated = false,
            )
            for (xsdTransformer in xsdTransformers) {
                irClass = xsdTransformer(element, irClass)
            }
            irTypes[qname] = Classes.ActualClass(irClass)
        }
    }

    for (complexType in complexTypes) {
        val complexTypeName = complexType.name
        val qName = IRTree.ClassName(targetNamespace.packageName, complexTypeName!!.remove())
        val sequence = complexType.sequence
        if (sequence != null && sequence.elements.size == 1 && (sequence.elements[0] as Element).name == null) {
            val element = sequence.elements[0] as Element
            val name = element.ref!!.split(":")[1]
            val typeAlias = IRTree.ClassName(targetNamespace.packageName, complexTypeName.remove())
            if (element.maxOccurs == "unbounded") {
                var irClass: IRTree.Class = IRTree.NormalClass(
                    packageName = typeAlias.packageName,
                    packageNameSuffix = "",
                    name = typeAlias.name.remove(),
                    namespace = targetNamespace,
                    serialName = complexType.name,
                    members = if (includeMembers) {
                        sequence.elements.map {
                            when (it) {
                                is Choice -> it.element
                                is Element -> it
                            }
                        }.mapToIr(typeAlias, this, xsdTransformers, irTypes)
                    } else {
                        emptyMap()
                    },
                    documentation = complexType.annotation?.documentation(),
                    isFault = false,
                    allOf = null,
                    discriminator = null,
                    deprecated = false,
                )
                for (xsdTransformer in xsdTransformers) {
                    irClass = xsdTransformer(complexType, irClass)
                }
                irTypes[typeAlias] = Classes.ActualClass(irClass)
            } else {
                val resolved = IRTree.ClassName(namespace(), name)
                if (resolved != typeAlias) {
                    irTypes[typeAlias] = Classes.TypeAlias(resolved)
                }
            }
        } else if (complexType.simpleContent != null) {
            var irClass: IRTree.Class = IRTree.NormalClass(
                packageName = targetNamespace.packageName,
                packageNameSuffix = "",
                name = complexTypeName.remove(),
                namespace = targetNamespace,
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
                                deprecated = false,
                            ),
                        )
                        for (it in complexType.simpleContent!!.extension.attributes) {
                            val s = it.mapToIr(this@toIr, irTypes)
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
                deprecated = false,
            )
            for (xsdTransformer in xsdTransformers) {
                irClass = xsdTransformer(complexType, irClass)
            }
            irTypes[qName] = Classes.ActualClass(irClass)
        } else {
            var irClass: IRTree.Class = IRTree.NormalClass(
                packageName = targetNamespace.packageName,
                packageNameSuffix = "",
                name = complexTypeName.remove(),
                namespace = targetNamespace,
                serialName = complexType.name,
                members = if (includeMembers) {
                    sequence?.elements?.map {
                        when (it) {
                            is Choice -> it.element
                            is Element -> it
                        }
                    }?.mapToIr(qName, this, xsdTransformers, irTypes) ?: emptyMap()
                } else {
                    emptyMap()
                },
                documentation = complexType.annotation?.documentation(),
                isFault = false,
                allOf = null,
                discriminator = null,
                deprecated = false,
            )
            for (xsdTransformer in xsdTransformers) {
                irClass = xsdTransformer(complexType, irClass)
            }
            irTypes[qName] = Classes.ActualClass(irClass)
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

private fun Schema.namespace(): String {
    return targetNamespace.packageName
}

private fun Map<IRTree.ClassName, Classes>.resolveMembers(): Set<IRTree.Class> = buildSet {
    for ((_, classes) in this@resolveMembers) {
        when (classes) {
            is Classes.TypeAlias -> continue
            is Classes.ActualClass -> when (val forClass = classes.forClass) {
                is IRTree.Type.Builtin -> continue
                is IRTree.Type.LIST -> continue
                is IRTree.Type.MAP -> continue
                is IRTree.Enum -> add(forClass)
                is IRTree.NormalClass -> {
                    add(
                        forClass.copy(
                            members = forClass.members.mapValues { (_, it) ->
                                it.resolve(this@resolveMembers)
                            },
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
    xsdTransformers: Collection<XsdTransformer>,
    topLevel: MutableMap<IRTree.ClassName, Classes>,
): Map<String, IRTree.Member> {
    return associate {
        val extension = it.complexType?.simpleContent?.extension
        val ref = (it.type ?: it.ref ?: it.name!!).split(":")
        val ns: String?
        fun createCustomWrapper(type: IRTree.Type): IRTree.Class {
            val qname = IRTree.ClassName(schema.targetNamespace.packageName, prefix.name + ref[0])
            var classe: IRTree.Class = IRTree.NormalClass(
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
                        }?.mapToIr(qname, schema, xsdTransformers, topLevel)
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
                            deprecated = false,
                        ),
                    )
                },
                documentation = it.annotation?.documentation(),
                allOf = null,
                discriminator = null,
                deprecated = false,
            )
            if (qname !in topLevel) {
                for (xsdTransformer in xsdTransformers) {
                    classe = xsdTransformer(it, classe)
                }
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
                val namespace = schema.namespace()
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
            topLevel.find(IRTree.ClassName(schema.targetNamespace.packageName, ref[0].remove())).let {
                if (extension != null && extension.attributes.isNotEmpty()) {
                    createCustomWrapper(it)
                } else {
                    it
                }
            }
        } else {
            val qname = IRTree.ClassName(schema.targetNamespace.packageName, ref[0])
            var classe: IRTree.Class = IRTree.NormalClass(
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
                }?.mapToIr(qname, schema, xsdTransformers, topLevel) ?: emptyMap(),
                documentation = it.annotation?.documentation(),
                allOf = null,
                discriminator = null,
                deprecated = false,
            )
            if (qname !in topLevel) {
                for (xsdTransformer in xsdTransformers) {
                    classe = xsdTransformer(it, classe)
                }
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
                namespace = schema.targetNamespace,
                documentation = it.annotation?.documentation(),
                xmlType = IRTree.XmlType.Element,
                requirements = buildList {
                    val restrictions = it.simpleType?.restriction
                    if (restrictions != null) {
                        val maxLength = restrictions.maxLength
                        if (maxLength != null) {
                            add(IRTree.Member.Requirement.MaxLength(maxLength.value))
                        }
                        val minList = restrictions.minLength
                        if (minList != null) {
                           add(IRTree.Member.Requirement.MinLength(minList.value))
                        }
                    }
                },
                isOverride = false,
                deprecated = false,
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
        val namespace = schema.namespace()
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
        requirements = emptyList(),
        isOverride = false,
        deprecated = false,
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
