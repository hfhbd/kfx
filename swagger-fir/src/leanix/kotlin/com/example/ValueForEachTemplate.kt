package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlinx.serialization.Serializable

/**
 * An object that contains information about how the value should be expanded according to a list field
 */
@Serializable
public data class ValueForEachTemplate(
  /**
   * The JUEL expressing targeting a list
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val elementOf: String? = null,
  /**
   * The filter to apply to each element
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val filter: String? = null,
)
