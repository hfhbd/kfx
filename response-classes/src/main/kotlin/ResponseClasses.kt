import app.softwork.serviceloader.*
import io.github.hfhbd.kfx.*
import io.github.hfhbd.kfx.codegen.*
import io.github.hfhbd.kfx.codegen.CodeGenTree.*
import io.github.hfhbd.kfx.codegen.CodeGenTree.Operation.ResponseBranches.*
import io.github.hfhbd.kfx.ir.*

@ServiceLoader(CodeGenTransformer::class)
class ResponseClasses : CodeGenTransformer {
    override operator fun invoke(codeGen: CodeGenTree, ir: IRTree): CodeGenTree {
        val classes = codeGen.classes.toMutableSet()
        val operations = codeGen.operations.mapTo(mutableSetOf()) {
            val newReturnTypeName = ClassName(
                it.packageName + ".results",
                listOf(it.name.replaceFirstChar { it.uppercase() } + "Result")
            )
            val newReturnType = NormalClass(
                packageName = newReturnTypeName.packageName,
                names = newReturnTypeName.names,
                isSealed = true,
                innerClasses = buildList {
                    val output = it.outputWrapperType ?: it.output
                    if (output != null) {
                        add(
                            NormalClass(
                                packageName = newReturnTypeName.packageName,
                                names = listOf("Success"),
                                members = listOfNotNull(
                                    Member(
                                        name = "body",
                                        type = output,
                                    )
                                ),
                                superInterfaces = listOf(newReturnTypeName)
                            )
                        )
                    }

                    if (it.notFound) {
                        add(
                            NormalClass(
                                packageName = newReturnTypeName.packageName,
                                names = listOf("NotFound"),
                                superInterfaces = listOf(newReturnTypeName)
                            )
                        )
                    }

                    val fault = it.faultWrapper ?: it.fault!!
                    add(
                        NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = listOf("Failure"),
                            members = listOfNotNull(
                                Member(
                                    name = "body",
                                    type = fault,
                                )
                            ),
                            superInterfaces = listOf(newReturnTypeName),
                        )
                    )
                }
            )

            classes.add(newReturnType)

            it.copy(
                fault = null,
                returnType = newReturnType,
                responseBranches = Operation.ResponseBranches(
                    Branch(
                        isCondition = NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = newReturnTypeName.names + listOf("Success"),
                        ),
                        statusCode = it.success,
                        response = Expression.Chain(
                            Expression.Response,
                            Expression.CallMember(
                                Member(
                                    name = "body",
                                    type = Type.Builtin.UNIT,
                                ),
                            ),
                        ),
                    ),
                    if (it.notFound) Branch(
                        isCondition = NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = newReturnTypeName.names + listOf("NotFound"),
                        ),
                        statusCode = StatusCode.NotFound,
                        response = null,
                    ) else null,
                    Branch(
                        isCondition = NormalClass(
                            packageName = newReturnTypeName.packageName,
                            names = newReturnTypeName.names + listOf("Failure"),
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
                        )
                    )
                )
            )
        }
        return codeGen.copy(
            operations = operations,
            classes = classes,
        )
    }
}
