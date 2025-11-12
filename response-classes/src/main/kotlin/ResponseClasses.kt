import app.softwork.serviceloader.*
import io.github.hfhbd.kfx.*
import io.github.hfhbd.kfx.codegen.*
import io.github.hfhbd.kfx.codegen.CodeGenTree.*
import io.github.hfhbd.kfx.codegen.CodeGenTree.Operation.ResponseBranches.*
import io.github.hfhbd.kfx.ir.*

@ServiceLoader(CodeGenTransformer::class)
class ResponseClasses : CodeGenTransformer {
    override operator fun invoke(codeGen: CodeGenTree, ir: IRTree): CodeGenTree {
        val classes = codeGen.classes.mapTo(mutableSetOf()) {
            when (it) {
                is NormalClass -> it.copy(
                    isFault = false,
                )
                is CodeGenTree.Enum -> it
            }
        }

        val operations = codeGen.operations.mapTo(mutableSetOf()) {
            val packageName = if (it.packageName.isBlank()) "results" else it.packageName + ".results"
            val newReturnTypeName = ClassName(
                packageName,
                listOf(it.name.replaceFirstChar { it.uppercase() } + "Result"),
            )
            val fault = it.faultWrapper ?: it.fault!!
            val newReturnType = NormalClass(
                packageName = newReturnTypeName.packageName,
                names = newReturnTypeName.names,
                isSealed = true,
                innerClasses = buildList {
                    val output = it.outputWrapperType ?: it.output
                    add(
                        NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = listOf("Success"),
                            members = buildList {
                                if (output != null) {
                                    add(Member(name = "body", type = output))
                                }
                                for (it in it.outputHeaders) {
                                    add(
                                        Member(
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
                            NormalClass(
                                packageName = newReturnTypeName.packageName,
                                names = listOf("NotFound"),
                                superInterfaces = listOf(newReturnTypeName),
                            ),
                        )
                    }

                    add(
                        NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = listOf("Failure"),
                            members = buildList {
                                add(Member(name = "body", type = fault))
                                for (it in it.faultHeaders) {
                                    add(Member(name = it.name.toCamelCase(), type = it.type, nullable = it.nullable))
                                }
                            },
                            superInterfaces = listOf(newReturnTypeName),
                        ),
                    )
                },
            )

            classes.add(newReturnType)

            it.copy(
                fault = null,
                returnType = newReturnType,
                responseBranches = Operation.ResponseBranches(
                    success = Branch(
                        isCondition = NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = newReturnTypeName.names + listOf("Success"),
                            members = it.outputHeaders.map {
                                Member(
                                    name = it.name.toCamelCase(),
                                    type = it.type,
                                )
                            },
                        ),
                        statusCode = it.success,
                        response = it.output?.let {
                            Expression.Chain(
                                Expression.Response,
                                Expression.CallMember(
                                    Member(
                                        name = "body",
                                        type = Type.Builtin.UNIT,
                                    ),
                                ),
                            )
                        },
                        headers = it.outputHeaders.associate {
                            it.name to (it.name.toCamelCase() to it.nullable)
                        },
                    ),
                    notFound = if (it.notFound) {
                        Branch(
                            isCondition = NormalClass(
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
                    fault = Branch(
                        isCondition = NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = newReturnTypeName.names + listOf("Failure"),
                            members = listOf(
                                Member(
                                    name = "body",
                                    type = fault,
                                ),
                            ) + it.faultHeaders.map {
                                Member(
                                    name = it.name.toCamelCase(),
                                    type = it.type,
                                )
                            },
                        ),
                        statusCode = StatusCode.InternalServerError,
                        response = Expression.Chain(
                            Expression.Response,
                            Expression.CallMember(
                                Member(
                                    name = "body",
                                    type = Type.Builtin.UNIT,
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
