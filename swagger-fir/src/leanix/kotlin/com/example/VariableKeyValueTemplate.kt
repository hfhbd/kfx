package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "VariableKeyValueTemplate")
public data class VariableKeyValueTemplate(
  /**
   * The basic expression of key
   */
  public val key: String,
  /**
   * The basic expression of value
   */
  public val `value`: String,
)
