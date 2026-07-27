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
                is CodeGenTree.StringEnum -> it.copy(
                    annotations = it.annotations + serializable(),
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

                is CodeGenTree.LongEnum -> it.copy(
                    annotations = it.annotations + serializable(
                        with = CodeGenTree.ClassName(
                            packageName = it.packageName,
                            names = it.names + "Companion",
                        ),
                    ),
                    values = it.values.map {
                        it.copy(
                            annotations = it.annotations,
                        )
                    },
                )

                is CodeGenTree.NormalClass -> it.copy(
                    annotations = buildList {
                        addAll(it.annotations)
                        if (!it.isResultClass) {
                            add(serializable())
                        }
                        val serialName = it.ir?.serialName
                        if (serialName != null) {
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

fun serializable(
    with: CodeGenTree.ClassName? = null,
): CodeGenTree.Annotation = CodeGenTree.Annotation(
    packageName = "kotlinx.serialization",
    names = listOf("Serializable"),
    values = if (with == null) {
        emptyMap()
    } else {
        mapOf(
        "with" to CodeGenTree.Expression.ClassLiteral(with),
    )
    },
)

fun serialName(value: String) = CodeGenTree.Annotation(
    "kotlinx.serialization",
    listOf("SerialName"),
    mapOf("value" to StringLiteral(value)),
)
