package io.github.hfhbd.kfx

import app.softwork.serviceloader.*
import io.github.hfhbd.kfx.codegen.CodeGenCreator
import io.github.hfhbd.kfx.codegen.CodeGenTree
import io.github.hfhbd.kfx.codegen.CodeGenTree.Expression.*
import io.github.hfhbd.kfx.ir.IRTree

@ServiceLoader(CodeGenCreator::class)
class KotlinxJsonCreator : KotlinxCoreCreator {
    override fun toCodeGen(ir: IRTree.NormalClass): CodeGenTree.NormalClass = CodeGenTree.NormalClass(
        packageName = ir.packageName,
        names = listOf(ir.name),
        members = ir.members.map { toCodeGen(it.value, it.key) }.filterNot {
            if (ir.discriminator != null) {
                it.name == ir.discriminator
            } else {
                false
            }
        },
        documentation = ir.documentation,
        isFault = ir.isFault,
        annotations = buildList {
            add(CodeGenTree.Annotation("kotlinx.serialization", listOf("Serializable"), emptyMap()))
            val discriminator = ir.discriminator
            if (discriminator != null && discriminator != "type") {
                add(
                    CodeGenTree.Annotation(
                        "kotlinx.serialization.json",
                        listOf("JsonClassDiscriminator"),
                        mapOf(
                            "discriminator" to StringLiteral(discriminator),
                        ),
                    ),
                )
            }
            if (ir.serialName != null) {
                add(serialName(ir.serialName!!))
            }
        },
        types = emptyList(),
        functions = emptyList(),
        isSealed = ir.discriminator != null,
        superInterfaces = listOfNotNull(ir.allOf?.let { toCodeGen(it) }),
    )
}
