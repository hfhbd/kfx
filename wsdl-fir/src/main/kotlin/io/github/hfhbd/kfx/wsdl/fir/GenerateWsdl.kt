package io.github.hfhbd.kfx.wsdl.fir

import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.StatusCode
import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.toCodeGen
import io.github.hfhbd.kfx.wsdl.model.OperationType
import io.github.hfhbd.kfx.wsdl.model.WSDL
import io.github.hfhbd.kfx.wsdl.model.xml
import io.github.hfhbd.kfx.xsd.fir.Classes
import io.github.hfhbd.kfx.xsd.fir.XsdTransformer
import io.github.hfhbd.kfx.xsd.fir.XsdTransformerFactory
import io.github.hfhbd.kfx.xsd.fir.find
import io.github.hfhbd.kfx.xsd.fir.namespace
import io.github.hfhbd.kfx.xsd.fir.packageName
import io.github.hfhbd.kfx.xsd.fir.resolve
import io.github.hfhbd.kfx.xsd.fir.toIr
import io.github.hfhbd.kfx.xsd.fir.trimDocumentation
import io.github.hfhbd.kfx.xsd.model.Schema
import nl.adaptivity.xmlutil.core.KtXmlReader
import java.io.InputStream
import java.nio.file.Path
import java.util.ServiceLoader

fun generateWsdl(
    wsdlFile: InputStream,
    import: (String) -> InputStream,
    outputDirectory: Path,
    wsdlTransformerFactories: Iterable<WsdlTransformerFactory> = ServiceLoader.load(WsdlTransformerFactory::class.java),
    xsdTransformerFactories: Iterable<XsdTransformerFactory> = ServiceLoader.load(XsdTransformerFactory::class.java),
    transformerFactories: Iterable<IrTransformer> = ServiceLoader.load(IrTransformer::class.java),
    codeGenCreator: CodeGenCreator = ServiceLoader.load(CodeGenCreator::class.java).single(),
    codeGenTransformer: Iterable<CodeGenTransformer> = ServiceLoader.load(CodeGenTransformer::class.java),
    codeGenerators: Iterable<CodeGenerator> = ServiceLoader.load(CodeGenerator::class.java),
) {
    val irTree = wsdlFile.createIr(
        wsdlTransformerFactories,
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
    wsdlTransformerFactories: Iterable<WsdlTransformerFactory>,
    xsdTransformerFactories: Iterable<XsdTransformerFactory>,
    import: (String) -> InputStream,
): IRTree {
    val xml = xml(
        wsdlTransformerFactories.map { it.serializerModule() },
    )
    val xsdXml = io.github.hfhbd.kfx.xsd.model.xml(
        xsdTransformerFactories.map { it.serializerModule() },
    )
    val wsdlTransformers = wsdlTransformerFactories.map {
        it.create()
    }

    val wsdl = xml.decodeFromReader(WSDL.serializer(), KtXmlReader(this))
    val irTree = wsdl.toIr(
        xsdTransformerFactories.map { it.create() },
    ) {
        var imported = xsdXml.decodeFromReader(Schema.serializer(), KtXmlReader(import(it)))
        for (wsdlTransformer in wsdlTransformers) {
            imported = wsdlTransformer(imported, it)
        }

        imported
    }
    return irTree
}

private fun WSDL.toIr(
    xsdTransformers: List<XsdTransformer>,
    import: (String) -> Schema,
): IRTree {
    val irTypes = mutableMapOf<IRTree.ClassName, Classes>()
    for (type in types) {
        for (schema in type.schemas) {
            toIr(
                schema = schema,
                xsdTransformers = emptyList(),
                includeMembers = false,
                irTypes = irTypes,
                import = import,
            )
        }
    }
    for (type in types) {
        for (schema in type.schemas) {
            toIr(
                schema,
                xsdTransformers = xsdTransformers,
                includeMembers = true,
                irTypes = irTypes,
                import = import,
            )
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
