package io.github.hfhbd.kfx.validation

import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenTree.Expression.IntLiteral
import io.github.hfhbd.kfx.ir.IRTree

@ServiceLoader(CodeGenTransformer::class)
class MaxLengthAnnotations : CodeGenTransformer {
    override fun invoke(codeGen: CodeGenTree, ir: IRTree): CodeGenTree = codeGen.copy(
        classes = codeGen.classes.mapTo(mutableSetOf()) { codeGenClass ->
            when (codeGenClass) {
                is CodeGenTree.Enum -> codeGenClass
                is CodeGenTree.NormalClass -> {
                    val irClass = ir.classes.single {
                        it.packageName == codeGenClass.packageName && it.name == codeGenClass.names.singleOrNull()
                    } as IRTree.NormalClass

                    codeGenClass.copy(
                        members = codeGenClass.members.updateMembers(irClass),
                    )
                }
            }
        },
    )

    private fun List<CodeGenTree.Member>.updateMembers(
        irClass: IRTree.NormalClass,
    ): List<CodeGenTree.Member> = map { codeGenMember ->
        val irMember = irClass.members[codeGenMember.name]!!
        if (codeGenMember.type == CodeGenTree.Type.Builtin.STRING) {
            codeGenMember.copy(
                annotations = buildList {
                    addAll(codeGenMember.annotations)
                    for (requirement in irMember.requirements) {
                        when (requirement) {
                            is IRTree.Member.Requirement.MaxLength ->
                                add(
                                    CodeGenTree.Annotation(
                                        "app.softwork.validation",
                                        listOf("MaxLength"),
                                        mapOf(
                                            "inclusive" to IntLiteral(requirement.inclusive),
                                        ),
                                    ),
                                )

                            is IRTree.Member.Requirement.MinLength -> add(
                                CodeGenTree.Annotation(
                                    "app.softwork.validation",
                                    listOf("MinLength"),
                                    mapOf(
                                        "inclusive" to IntLiteral(requirement.inclusive),
                                    ),
                                ),
                            )
                        }
                    }
                },
            )
        } else {
            codeGenMember
        }
    }
}
