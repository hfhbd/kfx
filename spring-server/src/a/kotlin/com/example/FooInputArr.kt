package com.example

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class FooInputArr(
  public val byBar: List<FooInputArrByBar>,
  public val byBaz: List<List<FooInputArrByBaz>> = emptyList(),
)
