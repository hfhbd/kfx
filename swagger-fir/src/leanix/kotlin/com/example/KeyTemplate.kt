package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "KeyTemplate")
public data class KeyTemplate(
  /**
   * The basic expression that is evaluated by the EL based on the content data
   */
  public val expr: String,
  /**
   * Optional matching and replacement patterns used on the evaluated expression
   */
  public val regexReplace: RegexReplace? = null,
)
