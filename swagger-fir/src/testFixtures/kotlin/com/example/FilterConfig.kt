package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Defines a filter for the content data that this processor is able to evaluate
 */
@Serializable
@SerialName(value = "FilterConfig")
public data class FilterConfig(
  /**
   * The regular expression of the content type this filter config should accept
   */
  public val type: String? = null,
  /**
   * The regular expression of the content id this filter config should accept
   */
  public val id: String? = null,
  /**
   * An EL expression to describe the advanced filter settings.
   */
  public val advanced: String? = null,
  /**
   * An EL expression to describe the onRead filter settings.
   */
  public val onRead: String? = null,
  /**
   * An EL expression to limit Fact Sheets that have changes during a given time window in the past. ISO-8601 duration format PnDTnHnMn is used
   */
  public val updatedInDuration: String? = null,
)
