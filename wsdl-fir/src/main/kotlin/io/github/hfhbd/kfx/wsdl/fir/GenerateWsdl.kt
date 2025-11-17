package io.github.hfhbd.kfx.wsdl.fir

import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.StatusCode
import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.toCodeGen
import io.github.hfhbd.kfx.wsdl.model.Annotation
import io.github.hfhbd.kfx.wsdl.model.Attribute
import io.github.hfhbd.kfx.wsdl.model.Choice
import io.github.hfhbd.kfx.wsdl.model.Element
import io.github.hfhbd.kfx.wsdl.model.OperationType
import io.github.hfhbd.kfx.wsdl.model.Schema
import io.github.hfhbd.kfx.wsdl.model.SimpleType
import io.github.hfhbd.kfx.wsdl.model.WSDL
import io.github.hfhbd.kfx.wsdl.model.XSD_NAMESPACE
import io.github.hfhbd.kfx.wsdl.model.xml
import nl.adaptivity.xmlutil.QName
import nl.adaptivity.xmlutil.core.KtXmlReader
import java.io.InputStream
import java.nio.file.Path
import java.util.ServiceLoader

fun generateWsdl(
    wsdlFile: InputStream,
    import: (String) -> InputStream,
    outputDirectory: Path,
    firTransformerFactories: Iterable<WsdlTransformerFactory> = ServiceLoader.load(WsdlTransformerFactory::class.java),
    transformerFactories: Iterable<IrTransformer> = ServiceLoader.load(IrTransformer::class.java),
    codeGenCreator: CodeGenCreator = ServiceLoader.load(CodeGenCreator::class.java).single(),
    codeGenTransformer: Iterable<CodeGenTransformer> = ServiceLoader.load(CodeGenTransformer::class.java),
    codeGenerators: Iterable<CodeGenerator> = ServiceLoader.load(CodeGenerator::class.java),
) {
    val irTree = wsdlFile.createIr(
        firTransformerFactories,
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
    wsdlTransformerFactories: Iterable<WsdlTransformerFactory>,
    import: (String) -> InputStream,
): IRTree {
    val xml = xml(
        wsdlTransformerFactories.map { it.serializerModule() },
    )

    val wsdl = xml.decodeFromReader(WSDL.serializer(), KtXmlReader(this))
    val irTree = wsdl.toIr(
        wsdlTransformerFactories.map { it.create() },
    ) {
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

private sealed interface Classes {
    data class TypeAlias(
        val actual: IRTree.ClassName,
        val serialName: String,
        val namespace: String,
        val ignore: Boolean,
    ) : Classes

    data class ActualClass(val forClass: IRTree.Type) : Classes
}

private fun WSDL.toIr(
    wsdlTransformers: Collection<WsdlTransformer>,
    import: (String) -> Schema,
): IRTree {
    val irTypes = mutableMapOf<IRTree.ClassName, Classes>()
    for (type in types) {
        for (schema in type.schemas) {
            toIr(schema, emptyList(), false, irTypes, import)
        }
    }
    for (type in types) {
        for (schema in type.schemas) {
            toIr(schema, wsdlTransformers, true, irTypes, import)
        }
    }

    val faults = portTypes.flatMap {
        it.operations.map {
            it.fault
        }.mapNotNull {
            it?.resolve(this)
        }
    }.toSet()

    val classes = irTypes.resolveMembers(faults)
    val operations = mutableSetOf<IRTree.Operation>()

    for (service in services) {
        val servicePort = service.port
        val bindingQName = servicePort.binding
        val binding = bindings.single {
            targetNamespace == bindingQName.namespace && it.name == bindingQName.localPart
        }
        val portTypeQName = binding.type
        val portType = portTypes.single {
            targetNamespace == portTypeQName.namespace && it.name == portTypeQName.localPart
        }
        for (bindingOperation in binding.operations) {
            val operation = portType.operations.single {
                it.name == bindingOperation.name
            }
            operations.add(
                IRTree.Operation(
                    packageName = targetNamespace.packageName,
                    name = operation.name.replaceFirstChar { it.lowercaseChar() },
                    documentation = operation.documentation?.trimDocumentation(),
                    location = service.port.address.location,
                    soapAction = bindingOperation.operation.soapAction,
                    input = operation.input.resolve(this).let { resolved ->
                        classes.firstOrNull {
                            it.packageName == resolved.packageName && it.name == resolved.name
                        }
                    },
                    output = operation.output.resolve(this).let { resolved ->
                        classes.firstOrNull {
                            it.packageName == resolved.packageName && it.name == resolved.name
                        }
                    },
                    notFound = false,
                    fault = operation.fault?.resolve(this)?.let { resolved ->
                        classes.firstOrNull {
                            it.packageName == resolved.packageName && it.name == resolved.name
                        } as IRTree.NormalClass?
                    },
                    path = null,
                    method = IRTree.Operation.HttpMethod.Post,
                    parameters = emptyList(),
                    queryParameters = emptyList(),
                    success = StatusCode.OK,
                    headers = emptyList(),
                    outputHeaders = emptyList(),
                    faultHeaders = emptyList(),
                    inputContentType = ContentType.TextXml,
                    outputContentType = ContentType.TextXml,
                    deprecated = false,
                ),
            )
        }
    }

    val irTree = IRTree(
        classes = classes,
        operations = operations,
        auth = emptySet(),
    )
    return irTree
}

private fun toIr(
    schema: Schema,
    wsdlTransformers: Collection<WsdlTransformer>,
    includeMembers: Boolean,
    irTypes: MutableMap<IRTree.ClassName, Classes>,
    import: (String) -> Schema,
) {
    for (import in schema.imports) {
        val schemaLocation = import.schemaLocation
        if (schemaLocation != null) {
            toIr(
                import(schemaLocation),
                wsdlTransformers,
                includeMembers,
                irTypes,
            )
        }
    }

    toIr(
        schema,
        wsdlTransformers,
        includeMembers,
        irTypes,
    )
}

private fun toIr(
    schema: Schema,
    wsdlTransformers: Collection<WsdlTransformer>,
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
            for (wsdlTransformer in wsdlTransformers) {
                irClass = wsdlTransformer(simpleType, irClass)
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
            val resolved = IRTree.ClassName((elementType.namespace ?: schema.targetNamespace) .packageName, elementType.localPart)
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
                members = element.complexType?.sequence?.elements?.map {
                    when (it) {
                        is Choice -> it.element
                        is Element -> it
                    }
                }?.mapToIr(qname, schema, wsdlTransformers, irTypes) ?: emptyMap(),
                isFault = false,
                allOf = null,
                discriminator = null,
                deprecated = false,
            )
            for (wsdlTransformer in wsdlTransformers) {
                irClass = wsdlTransformer(element, irClass)
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
                        }.mapToIr(typeAlias, schema, wsdlTransformers, irTypes)
                    } else {
                        emptyMap()
                    },
                    documentation = complexType.annotation?.documentation(),
                    isFault = false,
                    allOf = null,
                    discriminator = null,
                    deprecated = false,
                )
                for (wsdlTransformer in wsdlTransformers) {
                    irClass = wsdlTransformer(complexType, irClass)
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
                        put(
                            "value",
                            IRTree.Member(
                                type = complexType.simpleContent!!.extension.base.toBuiltin(),
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
            for (wsdlTransformer in wsdlTransformers) {
                irClass = wsdlTransformer(complexType, irClass)
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
                    sequence?.elements?.map {
                        when (it) {
                            is Choice -> it.element
                            is Element -> it
                        }
                    }?.mapToIr(qName, schema, wsdlTransformers, irTypes) ?: emptyMap()
                } else {
                    emptyMap()
                },
                documentation = complexType.annotation?.documentation(),
                isFault = false,
                allOf = null,
                discriminator = null,
                deprecated = false,
            )
            for (wsdlTransformer in wsdlTransformers) {
                irClass = wsdlTransformer(complexType, irClass)
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

private fun Map<IRTree.ClassName, Classes>.resolveMembers(faults: Set<IRTree.ClassName>): Set<IRTree.Class> = buildSet {
    val resolvedFaults = faults.mapTo(mutableSetOf()) {
        val found = find(it) as IRTree.NormalClass
        IRTree.ClassName(found.packageName, found.name)
    }
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
    wsdlTransformers: Collection<WsdlTransformer>,
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
                        }?.mapToIr(qname, schema, wsdlTransformers, topLevel)
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
                for (wsdlTransformer in wsdlTransformers) {
                    classe = wsdlTransformer(it, classe)
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
                }?.mapToIr(qname, schema, wsdlTransformers, topLevel) ?: emptyMap(),
                documentation = it.annotation?.documentation(),
                allOf = null,
                discriminator = null,
                deprecated = false,
            )
            if (qname !in topLevel) {
                for (wsdlTransformer in wsdlTransformers) {
                    classe = wsdlTransformer(it, classe)
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
        name.toBuiltin()
    } else {
        val namespace = (type.namespace ?: schema.targetNamespace).packageName
        val qname = IRTree.ClassName(namespace, name)
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

private fun Map<IRTree.ClassName, Classes>.find(qname: IRTree.ClassName): IRTree.Type =
    findOrNull(qname)
        ?: error("$qname not found in $keys")

private fun Map<IRTree.ClassName, Classes>.findOrNull(qname: IRTree.ClassName): IRTree.Type? = when (
    val value = get(qname)
) {
    is Classes.TypeAlias -> find(value.actual)
    is Classes.ActualClass -> value.forClass
    null -> null
}

private fun OperationType.resolve(
    definitions: WSDL,
): IRTree.ClassName {
    val qName = message

    val message = definitions.messages.single {
        it.name == qName.localPart
    }
    val ref = message.part.element
    val namespace = ref.namespace
    if (namespace == null) {
        return IRTree.ClassName(definitions.targetNamespace.packageName, ref.localPart)
    } else {
        return IRTree.ClassName(namespace.packageName, ref.localPart)
    }
}

internal fun QName.isXSD() = namespace == XSD_NAMESPACE

private fun SimpleType.toBuiltin(): IRTree.Type.Builtin? = restriction.base.takeIf {
    it.isXSD()
}?.toBuiltin()

private fun SimpleType.resolve(schema: Schema, irTypes: Map<IRTree.ClassName, Classes>): IRTree.Type = toBuiltin()
    ?: irTypes.find(IRTree.ClassName((restriction.base.namespace ?: schema.targetNamespace).packageName, restriction.base.localPart))

private fun QName.toBuiltin(): IRTree.Type.Builtin {
    require(isXSD())
    return localPart.toBuiltin()
}

private fun String.toBuiltin(): IRTree.Type.Builtin = when (this) {
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

internal val QName.namespace get() = namespaceURI.takeUnless { it.isEmpty() }
