package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DocumentFilter")
public data class DocumentFilter(
  /**
   * The document names to filter for
   */
  public val filter: String? = null,
)
