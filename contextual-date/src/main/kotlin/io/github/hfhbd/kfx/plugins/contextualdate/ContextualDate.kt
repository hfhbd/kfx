package io.github.hfhbd.kfx.plugins.contextualdate

import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenTree

@ServiceLoader(CodeGenTransformer::class)
class ContextualDate : CodeGenTransformer {
    override fun invoke(codeGen: CodeGenTree): CodeGenTree = codeGen.copy(
        classes = codeGen.classes.mapTo(mutableSetOf()) {
            when (it) {
                is CodeGenTree.Enum -> it

                is CodeGenTree.NormalClass -> it.copy(
                    members = it.members.map {
                        it.copy(
                            annotations = when {
                                it.type is CodeGenTree.Type.DateType -> {
                                    it.annotations + CodeGenTree.Annotation(
                                        "kotlinx.serialization",
                                        listOf("Contextual"),
                                        emptyMap(),
                                    )
                                }

                                else -> it.annotations
                            },
                        )
                    },
                )
            }
        },
    )
}
