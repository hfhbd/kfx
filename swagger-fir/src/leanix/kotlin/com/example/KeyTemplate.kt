package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "KeyTemplate")
public data class KeyTemplate(
  /**
   * The basic expression that is evaluated by the EL based on the content data
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val expr: String,
  /**
   * Optional matching and replacement patterns used on the evaluated expression
   */
  public val regexReplace: RegexReplace? = null,
)
