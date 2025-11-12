package io.github.hfhbd.kfx

fun Set<String>.getStatusCodes(): StatusCodes {
    val (successCodes, otherCodes) = partition { it.startsWith("2") }
    val unhandledClientErrors = setOf("401", "403", "404")
    val handlebarRequestErrors = otherCodes - unhandledClientErrors

    return when {
        setOf("default") == this -> StatusCodes(
            null,
            null,
        ) // not possible to decide if default is a successful or an error response
        // No default handling, so use the first status codes
        "default" !in this -> StatusCodes(successCodes.firstOrNull(), handlebarRequestErrors.firstOrNull())
        successCodes.isEmpty() -> StatusCodes("default", (handlebarRequestErrors - "default").firstOrNull())
        else -> StatusCodes(successCodes.firstOrNull(), "default")
    }
}

data class StatusCodes(
    val success: String?,
    val fault: String?,
)
