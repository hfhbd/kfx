package com.example

import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class FastRunStatsReport(
  /**
   * The duration of the execution expressed in ISO-8601 format PnDTnHnMn
   */
  public val duration: String? = null,
  /**
   * The number of elements processed as given by the LDIF input content
   */
  public val processedContentCount: Int? = null,
  /**
   * The number or processors that were part of the execution
   */
  public val processorCount: Int? = null,
)
