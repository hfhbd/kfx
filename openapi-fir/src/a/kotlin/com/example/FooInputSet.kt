package com.example

import kotlin.collections.List
import kotlin.collections.Set
import kotlin.collections.emptySet
import kotlinx.serialization.Serializable

@Serializable
public data class FooInputSet(
  public val byBar: List<FooInputSetByBar>,
  public val byBaz: Set<List<FooInputSetByBaz>>? = emptySet(),
)
