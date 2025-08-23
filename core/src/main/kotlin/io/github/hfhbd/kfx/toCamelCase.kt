package io.github.hfhbd.kfx

fun String.toCamelCase(): String = "[_\\-/.][a-zA-Z]".toRegex().replace(this) {
    it.value
        .replace("_", "")
        .replace("-", "")
        .replace("/", "")
        .replace(".", "")
        .uppercase()
}.replaceFirstChar {
    it.uppercaseChar()
}
