package io.github.hfhbd.kfx

fun String.toPascalCaseEnumValue(): String {
    val isUppercase = this == uppercase()
    val input = if (isUppercase) {
        lowercase()
    } else {
        this
    }

    return "[\\-/._\\s][a-zA-Z]".toRegex().replace(input) {
        it.value
            .replace("-", "")
            .replace("/", "")
            .replace(".", "")
            .replace("_", "")
            .replace(" ", "")
            .uppercase()
    }.replaceFirstChar {
        it.uppercaseChar()
    }
}

fun String.toCamelCase(): String = "[_\\-/][a-zA-Z]".toRegex().replace(this) {
    it.value
        .replace("_", "")
        .replace("-", "")
        .replace("/", "")
        .uppercase()
}

fun String.toPascalCase() = toCamelCase().replaceFirstChar { it.uppercaseChar() }

fun String.operationIdToCamelCase() = "[_\\-][a-zA-Z]".toRegex().replace(this) {
    it.value
        .replace("_", "")
        .replace("-", "")
        .uppercase()
}
