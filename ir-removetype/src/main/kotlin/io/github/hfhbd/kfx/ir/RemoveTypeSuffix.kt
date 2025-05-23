package io.github.hfhbd.kfx.ir

import app.softwork.serviceloader.*

@ServiceLoader(IrTransformer::class)
class RemoveTypeSuffix : IrTransformer {
    override operator fun invoke(irTree: IRTree): IRTree = irTree.copy(
        classes = irTree.classes.mapTo(mutableSetOf()) {
            when (it) {
                is IRTree.Enum -> it
                is IRTree.NormalClass -> it.removeType()
            }
        },
    )

    private fun IRTree.NormalClass.removeType(): IRTree.NormalClass {
        val memberNames = members.keys
        val newMembers = buildMap {
            for ((name, it) in members) {
                val newName = name.removeSuffix("Type")

                put(
                    if (newName in memberNames) {
                        name
                    } else {
                        newName
                    },
                    it,
                )
            }
        }

        return copy(
            members = newMembers,
        )
    }
}
