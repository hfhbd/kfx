package io.github.hfhbd.kfx.ir

import app.softwork.serviceloader.ServiceLoader

@ServiceLoader(IrTransformer::class)
class OData : IrTransformer {
    override fun invoke(irTree: IRTree): IRTree = irTree.copy(
        classes = irTree.classes.mapTo(mutableSetOf()) {
            it.transform()
        },
        operations = irTree.operations.mapTo(mutableSetOf()) {
            val newPath = it.path?.replace("""\$(\w)""".toRegex()) {
                "$" + "{'$'}" + it.groups[1]!!.value
            }?.replace("""'\$\{([^}]*)\}'""".toRegex()) {
                "'\${" + it.groups[1]!!.value.replaceFirstChar { it.lowercase() } + "}'"
            }
            it.copy(
                path = newPath,
                name = it.name.transform(),
                input = it.input?.transform(),
                output = it.output?.transform(),
                fault = it.fault?.transform(),
                parameters = it.parameters.map {
                    it.copy(
                        type = it.type.transform(),
                        name = it.name.replace("$", "").replaceFirstChar { it.lowercase() },
                        serialName = it.name,
                    )
                },
                queryParameters = it.queryParameters.map {
                    it.copy(
                        type = it.type.transform(),
                        name = it.name.replace("$", "").replaceFirstChar { it.lowercase() },
                        serialName = it.name,
                    )
                },
            )
        },
    )

    private fun IRTree.Type.transform(): IRTree.Type = when (this) {
        is IRTree.Type.Builtin -> this
        is IRTree.Class -> transform()
        is IRTree.Type.LIST -> list.transform()
        is IRTree.Type.MAP -> copy(
            key.transform(),
            value.transform(),
        )
    }

    private fun IRTree.NormalClass.transform(): IRTree.NormalClass = copy(
        name = name.transform(),
        members = members.mapValues { (_, it) ->
            it.copy(
                type = it.type.transform(),
            )
        },
    )

    private fun IRTree.Class.transform(): IRTree.Class {
        return when (this) {
            is IRTree.Enum -> copy(name = name.transform())

            is IRTree.NormalClass -> transform()
        }
    }
}

internal fun String.transform(): String {
    val underScore = replace("""[-_]+(.)""".toRegex()) {
        it.groups[1]!!.value.replaceFirstChar { it.uppercaseChar() }
    }
    val odataParam = underScore.replace(
        """\(([^)]*)\)""".toRegex(),
    ) {
        val s = it.groups[1]!!.value
        val bb = s.replace("""'\{([^}]*)\}'""".toRegex()) {
            val o = it.groups[1]!!.value
            o.replaceFirstChar { it.uppercaseChar() }
        }.split(",").joinToString("And") {
            if ("=" in it) {
                it.split("=")[1]
            } else {
                it
            }
        }
        "By$bb"
    }
    val ab = odataParam
        .replace("(", "")
        .replace("'", "")
        .replace(")", "")
        .replace(",", "And")
        .replace("""\$(.)""".toRegex()) {
            it.groups[1]!!.value.replaceFirstChar { it.uppercaseChar() }
        }
    return ab
}
