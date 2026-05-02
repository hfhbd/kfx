package io.github.hfhbd.kfx.creator.kotlin

import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenTree.Expression.StringLiteral

@ServiceLoader(CodeGenTransformer::class)
class KotlinxCoreCreator : CodeGenTransformer {
    override fun invoke(codeGen: CodeGenTree): CodeGenTree = codeGen.copy(
        classes = codeGen.classes.mapTo(mutableSetOf()) {
            when (it) {
                is CodeGenTree.Enum -> it.copy(
                    annotations = it.annotations + SERIALIZABLE,
                    values = it.values.map {
                        it.copy(
                            annotations = buildList {
                                addAll(it.annotations)
                                val serialName = it.ir?.serialName
                                if (serialName != null) {
                                    add(serialName(serialName))
                                }
                            },
                        )
                    },
                )

                is CodeGenTree.NormalClass -> it.copy(
                    annotations = buildList {
                        addAll(it.annotations)
                        if (!it.isResultClass) {
                            add(SERIALIZABLE)
                        }
                        val serialName = it.ir?.serialName
                        if (serialName != null && it.names.last() != serialName) {
                            add(serialName(serialName))
                        }
                    },
                    members = it.members.map {
                        it.copy(
                            annotations = buildList {
                                addAll(it.annotations)
                                val serialName = it.ir?.serialName
                                if (serialName != null) {
                                    add(serialName(serialName))
                                }
                            },
                        )
                    },
                )
            }
        },
    )
}

val SERIALIZABLE = CodeGenTree.Annotation("kotlinx.serialization", listOf("Serializable"), emptyMap())

fun serialName(value: String) = CodeGenTree.Annotation(
    "kotlinx.serialization",
    listOf("SerialName"),
    mapOf("value" to StringLiteral(value)),
)
