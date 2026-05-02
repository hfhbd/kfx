package com.example

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class OutboundDocumentFilter(
  /**
   * The document names to filter for
   */
  public val filter: String? = null,
)
