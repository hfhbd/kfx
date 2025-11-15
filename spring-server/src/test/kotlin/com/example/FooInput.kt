package com.example

import kotlinx.serialization.Serializable
import kotlin.Deprecated
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList

/**
 * Foo input sample
 */
@Serializable
public data class FooInput(
    /**
     * asdf
     */
    @Deprecated(message = "")
    public val s: String,
    public val `enum`: FooInputEnum? = null,
    public val arr: List<FooInputArr> = emptyList(),
)
