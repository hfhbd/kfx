package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "VariableKeyValueTemplate")
public data class VariableKeyValueTemplate(
  /**
   * The basic expression of key
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val key: String,
  /**
   * The basic expression of value
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val `value`: String,
)
