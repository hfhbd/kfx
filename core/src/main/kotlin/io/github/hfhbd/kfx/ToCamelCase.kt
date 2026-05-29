package io.github.hfhbd.kfx

fun String.toPascalCaseEnumValue(): String {
    val isUppercase = this == uppercase()
    val input = if (isUppercase) {
        lowercase()
    } else {
        this
    }

    return "[\\-/._\\s]\\w".toRegex().replace(input) {
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

fun String.toCamelCase(): String = "[_\\-/]\\w".toRegex().replace(this) {
    it.value
        .replace("_", "")
        .replace("-", "")
        .replace("/", "")
        .uppercase()
}

fun String.operationIdToCamelCase() = "[_\\-]\\w".toRegex().replace(this) {
    it.value
        .replace("_", "")
        .replace("-", "")
        .uppercase()
}

fun String.pathToOperationId() = split("/").joinToString("") {
    it.replaceFirstChar { it.uppercaseChar() }
}.replaceFirstChar { it.uppercaseChar() }
