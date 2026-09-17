package com.example

import io.github.hfhbd.validation.MaxLength
import io.github.hfhbd.validation.MinLength
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An object that contains information about how the value should be expanded according to a list field
 */
@Serializable
@SerialName(value = "ValueForEachTemplate")
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
