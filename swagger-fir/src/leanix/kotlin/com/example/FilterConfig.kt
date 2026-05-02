package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlinx.serialization.Serializable

/**
 * Defines a filter for the content data that this processor is able to evaluate
 */
@Serializable
public data class FilterConfig(
  /**
   * The regular expression of the content type this filter config should accept
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val type: String? = null,
  /**
   * The regular expression of the content id this filter config should accept
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val id: String? = null,
  /**
   * An EL expression to describe the advanced filter settings.
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val advanced: String? = null,
  /**
   * An EL expression to describe the onRead filter settings.
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val onRead: String? = null,
  /**
   * An EL expression to limit Fact Sheets that have changes during a given time window in the past. ISO-8601 duration format PnDTnHnMn is used
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val updatedInDuration: String? = null,
)
