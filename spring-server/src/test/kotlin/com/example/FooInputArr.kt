package com.example

import kotlinx.serialization.Serializable
import kotlin.collections.List
import kotlin.collections.emptyList

@Serializable
public data class FooInputArr(
    public val byBar: List<FooInputArrByBar>,
    public val byBaz: List<FooInputArrByBaz> = emptyList(),
)
