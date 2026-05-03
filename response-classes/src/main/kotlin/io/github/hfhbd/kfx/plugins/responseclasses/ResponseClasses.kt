package io.github.hfhbd.kfx.plugins.responseclasses

import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.StatusCode
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.toCamelCase

@ServiceLoader(CodeGenTransformer::class)
class ResponseClasses : CodeGenTransformer {
    override operator fun invoke(codeGen: CodeGenTree): CodeGenTree {
        val classes = codeGen.classes.mapTo(mutableSetOf()) {
            when (it) {
                is CodeGenTree.NormalClass -> it.copy(
                    isFault = false,
                )

                is CodeGenTree.Enum -> it
            }
        }

        val operations = codeGen.operations.mapTo(mutableSetOf()) {
            val packageName = if (it.packageName.isBlank()) "results" else it.packageName + ".results"
            val newReturnTypeName = CodeGenTree.ClassName(
                packageName,
                listOf(it.name.replaceFirstChar { it.uppercase() } + "Result"),
            )
            val fault = it.faultWrapper ?: it.fault!!
            val newReturnType = CodeGenTree.NormalClass(
                packageName = newReturnTypeName.packageName,
                names = newReturnTypeName.names,
                isSealed = true,
                innerClasses = buildList {
                    val output = it.outputWrapperType ?: it.output
                    add(
                        CodeGenTree.NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = listOf("Success"),
                            members = buildList {
                                if (output != null) {
                                    add(CodeGenTree.Member(name = "body", type = output))
                                }
                                for (it in it.outputHeaders) {
                                    add(
                                        CodeGenTree.Member(
                                            name = it.name.toCamelCase(),
                                            type = it.type,
                                            nullable = it.nullable,
                                        ),
                                    )
                                }
                            },
                            superInterfaces = listOf(newReturnTypeName),
                        ),
                    )

                    if (it.notFound) {
                        add(
                            CodeGenTree.NormalClass(
                                packageName = newReturnTypeName.packageName,
                                names = listOf("NotFound"),
                                superInterfaces = listOf(newReturnTypeName),
                            ),
                        )
                    }

                    add(
                        CodeGenTree.NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = listOf("Failure"),
                            members = buildList {
                                add(CodeGenTree.Member(name = "body", type = fault))
                                for (it in it.faultHeaders) {
                                    add(
                                        CodeGenTree.Member(
                                            name = it.name.toCamelCase(),
                                            type = it.type,
                                            nullable = it.nullable,
                                        ),
                                    )
                                }
                            },
                            superInterfaces = listOf(newReturnTypeName),
                        ),
                    )
                },
                isResultClass = true,
            )

            classes.add(newReturnType)

            it.copy(
                fault = null,
                returnType = newReturnType,
                responseBranches = CodeGenTree.Operation.ResponseBranches(
                    success = CodeGenTree.Operation.ResponseBranches.Branch(
                        isCondition = CodeGenTree.NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = newReturnTypeName.names + listOf("Success"),
                            members = it.outputHeaders.map {
                                CodeGenTree.Member(
                                    name = it.name.toCamelCase(),
                                    type = it.type,
                                )
                            },
                        ),
                        statusCode = it.success,
                        response = it.output?.let {
                            CodeGenTree.Expression.Chain(
                                CodeGenTree.Expression.Response,
                                CodeGenTree.Expression.CallMember(
                                    CodeGenTree.Member(
                                        name = "body",
                                        type = CodeGenTree.Type.Builtin.UNIT,
                                    ),
                                ),
                            )
                        },
                        headers = it.outputHeaders.associate {
                            it.name to (it.name.toCamelCase() to it.nullable)
                        },
                    ),
                    notFound = if (it.notFound) {
                        CodeGenTree.Operation.ResponseBranches.Branch(
                            isCondition = CodeGenTree.NormalClass(
                                packageName = newReturnTypeName.packageName,
                                names = newReturnTypeName.names + listOf("NotFound"),
                            ),
                            statusCode = StatusCode.NotFound,
                            response = null,
                            headers = emptyMap(),
                        )
                    } else {
                        null
                    },
                    fault = CodeGenTree.Operation.ResponseBranches.Branch(
                        isCondition = CodeGenTree.NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = newReturnTypeName.names + listOf("Failure"),
                            members = listOf(
                                CodeGenTree.Member(
                                    name = "body",
                                    type = fault,
                                ),
                            ) + it.faultHeaders.map {
                                CodeGenTree.Member(
                                    name = it.name.toCamelCase(),
                                    type = it.type,
                                )
                            },
                        ),
                        statusCode = StatusCode.InternalServerError,
                        response = CodeGenTree.Expression.Chain(
                            CodeGenTree.Expression.Response,
                            CodeGenTree.Expression.CallMember(
                                CodeGenTree.Member(
                                    name = "body",
                                    type = CodeGenTree.Type.Builtin.UNIT,
                                ),
                            ),
                        ),
                        headers = it.faultHeaders.associate {
                            it.name to (it.name.toCamelCase() to it.nullable)
                        },
                    ),
                ),
            )
        }
        return codeGen.copy(
            operations = operations,
            classes = classes,
        )
    }
}
