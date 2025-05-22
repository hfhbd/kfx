package io.github.hfhbd.kfx.openapi

import io.github.hfhbd.kfx.ir.IRTree

internal fun handleSealedClassMapping(irTree: IRTree, openapi: OpenApi): IRTree = irTree.copy(
    classes = irTree.classes.mapTo(mutableSetOf()) {
        when (it) {
            is IRTree.Enum -> it
            is IRTree.NormalClass -> if (it.allOf != null) {
                handleSealedClassMapping(
                    it,
                    openapi.components.schemas[it.allOf!!.qname]!! as OpenApi.Components.Schema.OBJECT,
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
    val serialName = sealedClass.discriminator!!.mapping.entries.singleOrNull {
        val s = "#/components/schemas/" + irClass.qName
        it.value == s
    }?.key

    return irClass.copy(
        serialName = serialName,
    )
}

private val IRTree.NormalClass.qName: String
    get() = if (packageName.isEmpty()) {
        name
    } else {
        "$packageName.$name"
    }
