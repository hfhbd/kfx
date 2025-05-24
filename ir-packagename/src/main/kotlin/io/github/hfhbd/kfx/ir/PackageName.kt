package io.github.hfhbd.kfx.ir

fun PackageName(packageName: String) = IrTransformer { irTree ->
    val result = irTree.copy(
        classes = irTree.classes.mapTo(mutableSetOf()) {
            it.changePackageName(packageName) as IRTree.Class
        },
        operations = irTree.operations.mapTo(mutableSetOf()) {
            it.changePackageName(packageName)
        },
        auth = irTree.auth.mapTo(mutableSetOf()) {
            when (it) {
                is IRTree.Auth.OAuth2 -> it.copy(
                    operation = it.operation.changePackageName(packageName),
                )

                is IRTree.Auth.Http -> it.copy(
                    packageName = if (it.packageName == "") packageName else it.packageName,
                )
            }
        },
    )
    result
}

private fun IRTree.Type.changePackageName(packageName: String): IRTree.Type = when (this) {
    is IRTree.Type.Builtin -> this
    is IRTree.Enum -> copy(
        packageName = if (this.packageName == "") packageName else this.packageName,
    )

    is IRTree.NormalClass -> copy(
        packageName = if (this.packageName == "") packageName else this.packageName,
        members = members.mapValues { (_, it) ->
            it.copy(
                type = it.type.changePackageName(packageName),
            )
        },
        allOf = allOf?.changePackageName(packageName),
        serialName = serialName ?: name,
    )

    is IRTree.Type.LIST -> copy(
        list = list.changePackageName(packageName),
    )

    is IRTree.Type.MAP -> copy(
        key = key.changePackageName(packageName),
        value = value.changePackageName(packageName),
    )
}

private fun IRTree.ClassName.changePackageName(packageName: String): IRTree.ClassName = copy(
    packageName = if (this.packageName == "") packageName else this.packageName,
)

private fun IRTree.Operation.changePackageName(packageName: String): IRTree.Operation = copy(
    packageName = if (this.packageName == "") packageName else this.packageName,
    output = output?.changePackageName(packageName),
    input = input?.changePackageName(packageName),
    fault = fault?.changePackageName(packageName) as IRTree.NormalClass?,
    parameters = parameters.map {
        it.copy(type = it.type.changePackageName(packageName))
    },
    queryParameters = queryParameters.map {
        it.copy(
            type = it.type.changePackageName(packageName),
        )
    },
)
