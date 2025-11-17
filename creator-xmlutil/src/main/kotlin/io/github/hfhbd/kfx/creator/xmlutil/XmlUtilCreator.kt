package io.github.hfhbd.kfx.creator.xmlutil

import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenTree.Expression.StringLiteral
import io.github.hfhbd.kfx.creator.kotlin.DEPRECATED
import io.github.hfhbd.kfx.creator.kotlin.KotlinxCoreCreator
import io.github.hfhbd.kfx.creator.kotlin.SERIALIZABLE
import io.github.hfhbd.kfx.creator.kotlin.serialName
import io.github.hfhbd.kfx.ir.IRTree

@ServiceLoader(CodeGenCreator::class)
class XmlUtilCreator : KotlinxCoreCreator {
    override fun toCodeGen(ir: IRTree.Member, name: String): CodeGenTree.Member = CodeGenTree.Member(
        name = name,
        type = toCodeGen(ir.type),
        nullable = ir.nullable,
        documentation = ir.documentation,
        annotations = buildList {
            when (ir.xmlType) {
                null -> {}
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
                    if (ir.serialName != null && ir.serialName != name) {
                        add(
                            serialName(ir.serialName!!),
                        )
                    }
                }
            }
            if (ir.deprecated) {
                add(DEPRECATED)
            }
        },
    )

    override fun toCodeGen(ir: IRTree.NormalClass): CodeGenTree.NormalClass = CodeGenTree.NormalClass(
        packageName = ir.packageName,
        names = listOf(ir.name),
        members = ir.members.map {
            toCodeGen(it.value, name = it.key)
        },
        computedProperties = if (ir.isValue) {
            val singleMember = ir.members[ir.members.keys.single()]!!
            (singleMember.type as? IRTree.NormalClass)?.members?.map {
                toCodeGen(it.value, name = it.key).copy(
                    annotations = emptyList(),
                )
            } ?: emptyList()
        } else {
            emptyList()
        },
        documentation = ir.documentation,
        isFault = ir.isFault,
        isValue = ir.isValue,
        annotations = buildList {
            val serialName = ir.serialName
            if (serialName != null) {
                add(SERIALIZABLE)
                add(
                    CodeGenTree.Annotation(
                        "nl.adaptivity.xmlutil.serialization",
                        listOf("XmlSerialName"),
                        mapOf(
                            "value" to StringLiteral(serialName),
                            "namespace" to StringLiteral(ir.namespace!!),
                        ),
                    ),
                )
            }
            if (ir.deprecated) {
                add(DEPRECATED)
            }
        },
        types = emptyList(),
        functions = emptyList(),
        superInterfaces = listOfNotNull(ir.allOf?.let { toCodeGen(it) }),
    )
}
