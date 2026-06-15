package com.example

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class FooInputSetByBaz(
  public val b: String? = null,
)
