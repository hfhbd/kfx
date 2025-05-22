package io.github.hfhbd.kfx

fun Set<String>.getStatusCodes(): StatusCodes {
    val (successCodes, otherCodes) = partition { it.startsWith("2") }
    val requestErrors = setOf("401", "403", "404")
    val excludeRequestErrors = otherCodes - requestErrors

    return when {
        setOf("default") == this -> StatusCodes(
            null,
            null,
        ) // not possible to decide if default is a successful or an error response
        // No default handling, so use the first status codes
        "default" !in this -> StatusCodes(successCodes.firstOrNull(), excludeRequestErrors.firstOrNull())
        successCodes.isEmpty() -> StatusCodes("default", (excludeRequestErrors - "default").firstOrNull())
        else -> StatusCodes(successCodes.firstOrNull(), "default")
    }
}

data class StatusCodes(
    val success: String?,
    val fault: String?,
)
