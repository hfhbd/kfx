package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlinx.serialization.Serializable

/**
 * The combination of a matching and a replacement pattern. The default matching pattern selects the whole string in group 1, while the default replace pattern replaces the matches with group 1
 */
@Serializable
public data class RegexReplace(
  /**
   * The matching pattern
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val match: String? = null,
  /**
   * The replacement pattern
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val replace: String? = null,
)
