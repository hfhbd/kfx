package com.example

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ImpactFilter")
public data class ImpactFilter(
  /**
   * A flag that specifies whether to read impacts belonging to Fact Sheet
   */
  public val readAll: Boolean? = null,
)
