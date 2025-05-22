package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The combination of a matching and a replacement pattern. The default matching pattern selects the whole string in group 1, while the default replace pattern replaces the matches with group 1
 */
@Serializable
@SerialName(value = "RegexReplace")
public data class RegexReplace(
  /**
   * The matching pattern
   */
  public val match: String? = null,
  /**
   * The replacement pattern
   */
  public val replace: String? = null,
)
