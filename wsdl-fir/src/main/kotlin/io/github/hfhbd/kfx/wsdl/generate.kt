package io.github.hfhbd.kfx.wsdl

import io.github.hfhbd.kfx.ContentType
import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenerator
import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.ir.IrTransformer
import io.github.hfhbd.kfx.toCodeGen
import io.github.hfhbd.kfx.xsd.Classes
import io.github.hfhbd.kfx.xsd.Schema
import io.github.hfhbd.kfx.xsd.XsdTransformer
import io.github.hfhbd.kfx.xsd.XsdTransformerFactory
import io.github.hfhbd.kfx.xsd.find
import io.github.hfhbd.kfx.xsd.packageName
import io.github.hfhbd.kfx.xsd.resolve
import io.github.hfhbd.kfx.xsd.toIr
import io.github.hfhbd.kfx.xsd.trimDocumentation
import nl.adaptivity.xmlutil.core.*
import java.io.InputStream
import java.nio.file.Path
import java.util.ServiceLoader

fun generate(
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
    val wsdlTransformers = wsdlTransformerFactories.map {
        it.create()
    }

    val reader = KtXmlReader(this)
    val wsdl = xml.decodeFromReader(WSDL.serializer(), reader)
    val irTree = wsdl.toIr(
        { prefix ->
            reader.getNamespaceURI(prefix)
        },
        xsdTransformerFactories.map { it.create() },
    ) {
        var imported = xml.decodeFromReader(Schema.serializer(), KtXmlReader(import(it)))
        for (wsdlTransformer in wsdlTransformers) {
            imported = wsdlTransformer(imported, it)
        }

        imported
    }
    return irTree
}

private fun WSDL.toIr(
    getNS: (String) -> String?,
    xsdTransformers: List<XsdTransformer>,
    import: (String) -> Schema,
): IRTree {
    val irTypes = mutableMapOf<IRTree.ClassName, Classes>()

    for (type in types) {
        for (schema in type.schemas) {
            schema.toIr(
                xsdTransformers = xsdTransformers,
                irTypes = irTypes,
                import = import,
            )
        }
    }

    for (message in messages) {
        val (ns, name) = message.part.element.split(":")
        val typeAlias = IRTree.ClassName(targetNamespace.packageName, message.name)
        val namespace = getNS(ns)
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
                deprecated = false,
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

private fun Type.resolve(definitions: WSDL): IRTree.ClassName {
    val name = message.split(":")[1]
    return IRTree.ClassName(definitions.targetNamespace.packageName, name)
}
