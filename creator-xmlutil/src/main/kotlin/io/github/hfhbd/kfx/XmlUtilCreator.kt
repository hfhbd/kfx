package io.github.hfhbd.kfx

import app.softwork.serviceloader.*
import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenTree.Expression.*
import io.github.hfhbd.kfx.ir.IRTree

@ServiceLoader(CodeGenCreator::class)
class XmlUtilCreator : KotlinxCoreCreator {
    override fun toCodeGen(ir: IRTree.Member, name: String): CodeGenTree.Member = CodeGenTree.Member(
        name = name,
        type = toCodeGen(ir.type),
        nullable = ir.nullable,
        documentation = ir.documentation,
        annotations = buildList {
            when (ir.xmlType!!) {
                IRTree.XmlType.Element -> {
                    add(
                        CodeGenTree.Annotation("nl.adaptivity.xmlutil.serialization", listOf("XmlElement"), emptyMap()),
                    )
                    add(
                        CodeGenTree.Annotation(
                            "nl.adaptivity.xmlutil.serialization",
                            listOf("XmlSerialName"),
                            mapOf(
                                "value" to StringLiteral(ir.serialName!!),
                                "namespace" to StringLiteral(ir.namespace!!),
                            ),
                        ),
                    )
                }

                IRTree.XmlType.Value -> add(
                    CodeGenTree.Annotation("nl.adaptivity.xmlutil.serialization", listOf("XmlValue"), emptyMap()),
                )

                IRTree.XmlType.CData -> {
                    add(CodeGenTree.Annotation("nl.adaptivity.xmlutil.serialization", listOf("XmlValue"), emptyMap()))
                    add(CodeGenTree.Annotation("nl.adaptivity.xmlutil.serialization", listOf("XmlCData"), emptyMap()))
                }

                IRTree.XmlType.Attribute -> {
                    add(
                        CodeGenTree.Annotation(
                            "kotlinx.serialization",
                            listOf("SerialName"),
                            mapOf("value" to StringLiteral(ir.serialName!!)),
                        ),
                    )
                }
            }
        },
    )

    override fun toCodeGen(ir: IRTree.NormalClass): CodeGenTree.NormalClass = CodeGenTree.NormalClass(
        packageName = ir.packageName,
        names = listOf(ir.name),
        members = ir.members.map { toCodeGen(it.value, it.key) },
        documentation = ir.documentation,
        isFault = ir.isFault,
        annotations = listOf(
            CodeGenTree.Annotation("kotlinx.serialization", listOf("Serializable"), emptyMap()),
            CodeGenTree.Annotation(
                "nl.adaptivity.xmlutil.serialization",
                listOf("XmlSerialName"),
                mapOf(
                    "value" to StringLiteral(ir.name),
                    "namespace" to StringLiteral(ir.namespace!!),
                ),
            ),
        ),
        types = emptyList(),
        functions = emptyList(),
        superInterfaces = listOfNotNull(ir.allOf?.let { toCodeGen(it) }),
    )
}
