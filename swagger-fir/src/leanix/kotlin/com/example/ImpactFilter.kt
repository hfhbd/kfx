package com.example

import kotlin.Boolean
import kotlinx.serialization.Serializable

@Serializable
public data class ImpactFilter(
  /**
   * A flag that specifies whether to read impacts belonging to Fact Sheet
   */
  public val readAll: Boolean? = null,
)
