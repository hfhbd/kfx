package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Specify the condition to collect data. Juel expression can be used in all corresponding values.
 */
@Serializable
@SerialName(value = "MetricRules")
public data class MetricRules(
  /**
   * Identify the key for the rule
   */
  public val key: String? = null,
  /**
   * Identify the operation that is used to compare values
   */
  public val comparator: String? = null,
  /**
   * The value used in the comparison operation
   */
  public val compareWith: String? = null,
)
