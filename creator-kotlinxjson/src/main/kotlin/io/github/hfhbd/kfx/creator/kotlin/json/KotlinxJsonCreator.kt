package io.github.hfhbd.kfx.creator.kotlin.json

import app.softwork.serviceloader.ServiceLoader
import io.github.hfhbd.kfx.codegen.CodeGenTransformer
import io.github.hfhbd.kfx.codegen.CodeGenTree

@ServiceLoader(CodeGenTransformer::class)
class KotlinxJsonCreator : CodeGenTransformer {
    override fun invoke(codeGen: CodeGenTree): CodeGenTree = codeGen.copy(
        classes = codeGen.classes.mapTo(mutableSetOf()) {
            when (it) {
                is CodeGenTree.Enum -> it

                is CodeGenTree.NormalClass -> {
                    val discriminator = it.ir?.discriminator

                    it.copy(
                        annotations = buildList {
                            addAll(it.annotations)

                            if (discriminator != null && discriminator != "type") {
                                add(
                                    CodeGenTree.Annotation(
                                        "kotlinx.serialization.json",
                                        listOf("JsonClassDiscriminator"),
                                        mapOf(
                                            "discriminator" to CodeGenTree.Expression.StringLiteral(
                                                discriminator,
                                            ),
                                        ),
                                    ),
                                )
                            }
                        },
                        members = buildList {
                            for (member in it.members) {
                                if (member.name != discriminator) {
                                    val newType = member.type.replaceUnknown()
                                    add(member.copy(type = newType))
                                }
                            }
                        },
                        isSealed = discriminator != null,
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
        CodeGenTree.Type.Unknown -> JsonObject
        else -> this
    }

    val JsonObject = CodeGenTree.NormalClass(
        packageName = "kotlinx.serialization.json",
        names = listOf("JsonObject"),
        provided = true,
    )
}
