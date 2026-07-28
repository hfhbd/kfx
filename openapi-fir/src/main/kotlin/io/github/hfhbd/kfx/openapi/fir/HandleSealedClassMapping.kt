package io.github.hfhbd.kfx.openapi.fir

import io.github.hfhbd.kfx.ir.IRTree
import io.github.hfhbd.kfx.openapi.model.OpenApi

internal fun handleSealedClassMapping(irTree: IRTree, openapi: OpenApi): IRTree = irTree.copy(
    classes = irTree.classes.mapTo(mutableSetOf()) {
        when (it) {
            is IRTree.Enum -> it

            is IRTree.NormalClass -> if (it.allOf != null) {
                val allOfQname = it.allOf!!.qname
                val allOf = openapi.components.schemas.entries.single {
                    it.key.equals(allOfQname, ignoreCase = true)
                }.value
                handleSealedClassMapping(
                    it,
                    allOf as OpenApi.Components.Schema.OBJECT,
                )
            } else {
                it
            }
        }
    },
)

private fun handleSealedClassMapping(
    irClass: IRTree.NormalClass,
    sealedClass: OpenApi.Components.Schema.OBJECT,
): IRTree.NormalClass {
    val serialName = sealedClass.discriminator?.mapping?.entries?.singleOrNull {
        val s = "#/components/schemas/" + irClass.qName
        it.value == s
    }?.key

    return irClass.copy(
        serialName = serialName,
    )
}

internal val IRTree.Class.qName: String
    get() = if (packageName.isEmpty()) {
        name
    } else {
        "$packageName.$name"
    }
