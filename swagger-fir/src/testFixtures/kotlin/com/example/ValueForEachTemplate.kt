package com.example

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
  public val elementOf: String? = null,
  /**
   * The filter to apply to each element
   */
  public val filter: String? = null,
)
