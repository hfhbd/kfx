package com.example

import testOpenApi
import kotlin.test.Test

class OpenApiTesting {
    @Test
    fun results() {
        testOpenApi("results", "a")
    }
}

public data class Fault(
    public val httpReturnCode: Int,
    public val input: String? = null,
    public val message: String,
    public val stackTrace: String? = null,
    public val statusCode: Int? = null,
)

data object FooInput
