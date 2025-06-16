package com.example

import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class FooInputArr(
  public val byBar: List<FooInputArrByBar>,
  public val byBaz: List<FooInputArrByBaz>,
)
