package io.github.hfhbd.kfx.xsd.fir

import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.toCodeGen
import io.github.hfhbd.kfx.xsd.model.Annotation
import io.github.hfhbd.kfx.xsd.model.Attribute
import io.github.hfhbd.kfx.xsd.model.Choice
import io.github.hfhbd.kfx.xsd.model.Element
import io.github.hfhbd.kfx.xsd.model.Schema
import io.github.hfhbd.kfx.xsd.model.SimpleType
import io.github.hfhbd.kfx.xsd.model.XSD_NAMESPACE
import io.github.hfhbd.kfx.xsd.model.xml
import nl.adaptivity.xmlutil.QName
import nl.adaptivity.xmlutil.core.KtXmlReader
import java.io.InputStream
import java.nio.file.Path
import java.util.ServiceLoader

fun generateXsd(
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

    val schema = xml.decodeFromReader(Schema.serializer(), KtXmlReader(this))
    val irTree = schema.toIr(xsdTransformerFactories.map { it.create() }) {
        import(it).use {
            xml.decodeFromReader(Schema.serializer(), KtXmlReader(it))
        }
    }
    return irTree
}

val String.packageName: String
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
    data class TypeAlias(
        val actual: IRTree.ClassName,
        val serialName: String,
        val namespace: String,
        val ignore: Boolean,
    ) : Classes

    data class ActualClass(val forClass: IRTree.Type) : Classes
}

private fun Schema.toIr(
    xsdTransformers: Collection<XsdTransformer>,
    import: (String) -> Schema,
): IRTree {
    val irTypes = mutableMapOf<IRTree.ClassName, Classes>()
    toIr(this, emptyList(), false, irTypes, import)
    toIr(this, xsdTransformers, true, irTypes, import)

    return IRTree(
        classes = irTypes.resolveMembers(),
        operations = emptySet(),
        auth = emptySet(),
    )
}

fun toIr(
    schema: Schema,
    xsdTransformers: Collection<XsdTransformer>,
    includeMembers: Boolean,
    irTypes: MutableMap<IRTree.ClassName, Classes>,
    import: (String) -> Schema,
) {
    for (import in schema.imports) {
        val schemaLocation = import.schemaLocation
        if (schemaLocation != null) {
            val imported = import(schemaLocation)
            toIr(
                imported,
                xsdTransformers,
                includeMembers,
                irTypes,
            )
        }
    }

    toIr(
        schema,
        xsdTransformers,
        includeMembers,
        irTypes,
    )
}

private fun toIr(
    schema: Schema,
    xsdTransformers: Collection<XsdTransformer>,
    includeMembers: Boolean,
    irTypes: MutableMap<IRTree.ClassName, Classes>,
) {
    for (simpleType in schema.simpleType) {
        if (simpleType.restriction.enumeration.isNotEmpty()) {
            val packageName = schema.targetNamespace.packageName
            val simpleTypeName = simpleType.name
            var irClass: IRTree.Class = IRTree.Enum(
                packageName = schema.targetNamespace.packageName,
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
            val typeAlias = IRTree.ClassName(schema.targetNamespace.packageName, simpleType.name!!)
            val resolved = simpleType.toBuiltin()!!
            irTypes[typeAlias] = Classes.ActualClass(resolved)
        }
    }
    for (element in schema.elements) {
        val elementType = element.type
        if (elementType != null) {
            val typeAlias = IRTree.ClassName(schema.targetNamespace.packageName, element.name!!)
            val resolved = IRTree.ClassName(
                (elementType.namespace ?: schema.targetNamespace).packageName,
                elementType.localPart,
            )
            if (resolved != typeAlias) {
                irTypes[typeAlias] = Classes.TypeAlias(resolved, element.name!!, schema.targetNamespace, ignore = false)
            }
        } else {
            val elementName = element.name!!
            val packageName = schema.targetNamespace.packageName
            val qname = IRTree.ClassName(packageName, elementName)
            var irClass: IRTree.Class = IRTree.NormalClass(
                packageName = schema.targetNamespace.packageName,
                packageNameSuffix = "",
                name = elementName,
                serialName = elementName,
                namespace = schema.targetNamespace,
                documentation = element.annotation?.documentation(),
                members = (
                    element.complexType?.sequence?.elements?.map {
                        when (it) {
                            is Choice -> it.element
                            is Element -> it
                        }
                    }?.mapToIr(
                        qname,
                        schema,
                        xsdTransformers,
                        irTypes,
                    ) ?: emptyMap()
                    ) + (
                    element.complexType?.attributes?.map {
                        it.mapToIr(schema, irTypes)
                    } ?: emptyList()
                    ),
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

    for (complexType in schema.complexTypes) {
        val complexTypeName = complexType.name
        val qName = IRTree.ClassName(schema.targetNamespace.packageName, complexTypeName!!)
        val sequence = complexType.sequence
        if (sequence != null && sequence.elements.size == 1 && (sequence.elements[0] as Element).name == null) {
            val element = sequence.elements[0] as Element
            val typeAlias = IRTree.ClassName(schema.targetNamespace.packageName, complexTypeName)
            if (element.maxOccurs == "unbounded") {
                var irClass: IRTree.Class = IRTree.NormalClass(
                    packageName = typeAlias.packageName,
                    packageNameSuffix = "",
                    name = typeAlias.name,
                    namespace = schema.targetNamespace,
                    serialName = complexType.name,
                    members = if (includeMembers) {
                        sequence.elements.map {
                            when (it) {
                                is Choice -> it.element
                                is Element -> it
                            }
                        }.mapToIr(typeAlias, schema, xsdTransformers, irTypes) + complexType.attributes.map {
                            it.mapToIr(schema, irTypes)
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
                irTypes[typeAlias] = Classes.ActualClass(irClass)
            } else {
                val ref = element.ref!!
                val namespace = (ref.namespace ?: schema.targetNamespace).packageName
                val resolved = IRTree.ClassName(namespace, ref.localPart)
                if (resolved != typeAlias) {
                    irTypes[typeAlias] = Classes.TypeAlias(resolved, ref.localPart, namespace, ignore = false)
                }
            }
        } else if (complexType.simpleContent != null) {
            var irClass: IRTree.Class = IRTree.NormalClass(
                packageName = schema.targetNamespace.packageName,
                packageNameSuffix = "",
                name = complexTypeName,
                namespace = schema.targetNamespace,
                serialName = complexType.name,
                members = if (includeMembers) {
                    buildMap {
                        val base = complexType.simpleContent!!.extension.base
                        val type = if (base.isXSD()) {
                            base.toBuiltin()
                        } else {
                            irTypes.find(
                                IRTree.ClassName(base.namespace!!.packageName, base.localPart)
                            )
                        }
                        put(
                            "value",
                            IRTree.Member(
                                type =  type,
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
                deprecated = false,
            )
            for (xsdTransformer in xsdTransformers) {
                irClass = xsdTransformer(complexType, irClass)
            }
            irTypes[qName] = Classes.ActualClass(irClass)
        } else {
            var irClass: IRTree.Class = IRTree.NormalClass(
                packageName = schema.targetNamespace.packageName,
                packageNameSuffix = "",
                name = complexTypeName,
                namespace = schema.targetNamespace,
                serialName = complexType.name,
                members = if (includeMembers) {
                    (
                        sequence?.elements?.map {
                            when (it) {
                                is Choice -> it.element
                                is Element -> it
                            }
                        }?.mapToIr(qName, schema, xsdTransformers, irTypes) ?: emptyMap()
                        ) + complexType.attributes.map {
                        it.mapToIr(schema, irTypes)
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
        }
    }
}

private fun Annotation.documentation(): String? {
    return documentation?.values?.mapNotNull {
        (it as? String)?.trimDocumentation() ?: return@mapNotNull null
    }?.joinToString("")?.trim()
}

fun String.trimDocumentation(): String {
    val docs = split("\n")
    return docs.joinToString(" ") {
        it.trim()
    }.trim()
}

private fun Map<IRTree.ClassName, Classes>.resolveMembers(): Set<IRTree.Class> = buildSet {
    for ((className, classes) in this@resolveMembers) {
        when (classes) {
            is Classes.TypeAlias if classes.ignore -> continue
            is Classes.TypeAlias -> {
                val found = this@resolveMembers.find(classes.actual) as IRTree.NormalClass
                add(
                    IRTree.NormalClass(
                        packageName = className.packageName,
                        packageNameSuffix = "",
                        name = className.name,
                        serialName = classes.serialName,
                        namespace = classes.namespace,
                        members = mapOf(
                            "_value" to IRTree.Member(
                                type = found,
                                nullable = false,
                                serialName = null,
                                namespace = null,
                                documentation = null,
                                xmlType = null,
                                requirements = emptyList(),
                                isOverride = false,
                                deprecated = false,
                            ),
                        ),
                        documentation = found.documentation,
                        isFault = false,
                        isValue = true,
                        discriminator = null,
                        allOf = null,
                        deprecated = false,
                    ),
                )
            }

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

fun IRTree.Member.resolve(from: Map<IRTree.ClassName, Classes>): IRTree.Member {
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
        val ref = it.type ?: it.ref

        fun createCustomWrapper(type: IRTree.Type): IRTree.Class {
            val qname = IRTree.ClassName(schema.targetNamespace.packageName, prefix.name + (ref?.localPart ?: it.name))
            var classe: IRTree.Class = IRTree.NormalClass(
                packageName = qname.packageName,
                packageNameSuffix = "",
                name = qname.name,
                serialName = ref?.localPart ?: it.name!!,
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

                        for (attribute in complexType.attributes) {
                            val ir = attribute.mapToIr(schema, topLevel)
                            put(ir.first, ir.second)
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

        val type: IRTree.Type = if (ref != null) {
            if (ref.isXSD()) {
                ref.toBuiltin().let {
                    if (extension != null && extension.attributes.isNotEmpty()) {
                        createCustomWrapper(it)
                    } else {
                        it
                    }
                }
            } else {
                val namespace = (ref.namespace ?: schema.targetNamespace).packageName
                val qname = IRTree.ClassName(namespace, ref.localPart)
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
        } else if (topLevel.findOrNull(
                IRTree.ClassName(
                    schema.targetNamespace.packageName,
                    it.name!!,
                ),
            ) != null
        ) {
            topLevel.find(IRTree.ClassName(schema.targetNamespace.packageName, it.name!!)).let {
                if (extension != null && extension.attributes.isNotEmpty()) {
                    createCustomWrapper(it)
                } else {
                    it
                }
            }
        } else {
            val qname = IRTree.ClassName(schema.targetNamespace.packageName, it.name!!)
            var classe: IRTree.Class = IRTree.NormalClass(
                packageName = qname.packageName,
                packageNameSuffix = "",
                name = qname.name,
                serialName = it.name!!,
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

        val elementName = it.name ?: ref!!.localPart

        elementName.replaceFirstChar { it.lowercaseChar() } to
            IRTree.Member(
                type = if (it.maxOccurs == "unbounded") {
                    IRTree.Type.LIST(type)
                } else {
                    type
                },
                nullable = it.nillable == true || it.minOccurs == "0",
                serialName = elementName,
                namespace = if (ref == null) {
                    schema.targetNamespace
                } else {
                    ref.namespace
                },
                documentation = it.annotation?.documentation(),
                xmlType = IRTree.XmlType.Element,
                requirements = emptyList(),
                isOverride = false,
                deprecated = false,
            )
    }
}

@JvmName("mapToIrAttributes")
private fun Attribute.mapToIr(
    schema: Schema,
    topLevel: Map<IRTree.ClassName, Classes>,
): Pair<String, IRTree.Member> {
    val type = requireNotNull(type)
    val irType = if (type.namespace == XSD_NAMESPACE) {
        type.toBuiltin()
    } else {
        val namespace = (type.namespace ?: schema.targetNamespace).packageName
        val qname = IRTree.ClassName(namespace, type.localPart)
        topLevel.find(qname)
    }

    return this.name.replaceFirstChar { it.lowercaseChar() } to IRTree.Member(
        type = irType,
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

fun Map<IRTree.ClassName, Classes>.find(qname: IRTree.ClassName): IRTree.Type =
    findOrNull(qname)
        ?: error("$qname not found in $keys")

private fun Map<IRTree.ClassName, Classes>.findOrNull(qname: IRTree.ClassName): IRTree.Type? = when (
    val value = get(qname)
) {
    is Classes.TypeAlias -> find(value.actual)
    is Classes.ActualClass -> value.forClass
    null -> null
}

internal fun QName.isXSD() = namespace == XSD_NAMESPACE

private fun SimpleType.toBuiltin(): IRTree.Type.Builtin? = restriction.base.takeIf {
    it.isXSD()
}?.toBuiltin()

private fun SimpleType.resolve(schema: Schema, irTypes: Map<IRTree.ClassName, Classes>): IRTree.Type = toBuiltin()
    ?: irTypes.find(
        IRTree.ClassName(
            (restriction.base.namespace ?: schema.targetNamespace).packageName,
            restriction.base.localPart,
        ),
    )

private fun QName.toBuiltin(): IRTree.Type.Builtin {
    require(isXSD()) {
        toString()
    }
    return when (localPart) {
        "string" -> IRTree.Type.Builtin.STRING
        "anyURI" -> IRTree.Type.Builtin.STRING
        "normalizedString" -> IRTree.Type.Builtin.STRING
        "dateTime" -> IRTree.Type.DateType.INSTANT
        "date" -> IRTree.Type.DateType.DATE
        "base64Binary" -> IRTree.Type.Builtin.STRING
        "boolean" -> IRTree.Type.Builtin.BOOLEAN
        "integer", "int" -> IRTree.Type.Builtin.INT
        "long" -> IRTree.Type.Builtin.LONG
        "decimal" -> IRTree.Type.Builtin.DOUBLE
        else -> error("Not supported builtin: $this")
    }
}

public val QName.namespace get() = namespaceURI.takeUnless { it.isEmpty() }
