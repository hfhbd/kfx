package io.github.hfhbd.kfx.creator.xmlutil

import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenTree.Expression.StringLiteral
import io.github.hfhbd.kfx.creator.kotlin.serialName
import io.github.hfhbd.kfx.ir.IRTree

@ServiceLoader(CodeGenTransformer::class)
class XmlUtilCreator : CodeGenTransformer {
    override fun invoke(codeGen: CodeGenTree): CodeGenTree = codeGen.copy(
        classes = codeGen.classes.mapTo(mutableSetOf()) {
            when (it) {
                is CodeGenTree.Enum -> it

                is CodeGenTree.NormalClass -> {
                    it.copy(
                        annotations = buildList {
                            addAll(it.annotations)
                            add(
                                CodeGenTree.Annotation(
                                    "nl.adaptivity.xmlutil.serialization",
                                    listOf("XmlSerialName"),
                                    mapOf(
                                        "value" to StringLiteral(it.ir!!.serialName!!),
                                        "namespace" to StringLiteral(it.ir!!.namespace!!),
                                    ),
                                ),
                            )
                        },
                        members = buildList {
                            for (member in it.members) {
                                val newType = member.type.replaceUnknown()

                                val annotations = buildList {
                                    addAll(member.annotations)
                                    when (member.ir?.xmlType) {
                                        null -> {}

                                        IRTree.XmlType.Element -> {
                                            add(
                                                CodeGenTree.Annotation(
                                                    "nl.adaptivity.xmlutil.serialization",
                                                    listOf("XmlElement"),
                                                    emptyMap(),
                                                ),
                                            )
                                            add(
                                                CodeGenTree.Annotation(
                                                    "nl.adaptivity.xmlutil.serialization",
                                                    listOf("XmlSerialName"),
                                                    mapOf(
                                                        "value" to StringLiteral(member.ir!!.serialName!!),
                                                        "namespace" to StringLiteral(member.ir!!.namespace!!),
                                                    ),
                                                ),
                                            )
                                        }

                                        IRTree.XmlType.Value -> add(
                                            CodeGenTree.Annotation(
                                                "nl.adaptivity.xmlutil.serialization",
                                                listOf("XmlValue"),
                                                emptyMap(),
                                            ),
                                        )

                                        IRTree.XmlType.CData -> {
                                            add(
                                                CodeGenTree.Annotation(
                                                    "nl.adaptivity.xmlutil.serialization",
                                                    listOf("XmlValue"),
                                                    emptyMap(),
                                                ),
                                            )
                                            add(
                                                CodeGenTree.Annotation(
                                                    "nl.adaptivity.xmlutil.serialization",
                                                    listOf("XmlCData"),
                                                    emptyMap(),
                                                ),
                                            )
                                        }

                                        IRTree.XmlType.Attribute -> {}
                                    }
                                }

                                add(
                                    member.copy(
                                        annotations = annotations,
                                        type = newType,
                                    ),
                                )
                            }
                        },
                    )
                }
            }
        },
        operations = codeGen.operations.mapTo(mutableSetOf()) {
            it.copy(
                input = it.input?.replaceUnknown(),
                output = it.output?.replaceUnknown(),
                returnType = it.returnType?.replaceUnknown(),
                inputWrapperType = it.inputWrapperType?.replaceUnknown(),
                outputWrapperType = it.outputWrapperType?.replaceUnknown(),
                faultWrapper = it.faultWrapper?.replaceUnknown(),
            )
        },
    )

    private fun CodeGenTree.Type.replaceUnknown() = when (this) {
        CodeGenTree.Type.Unknown -> CodeGenTree.NormalClass(
            packageName = "nl.adaptivity.xmlutil.util",
            names = listOf("CompactFragment"),
            provided = true,
        )

        else -> this
    }
}
