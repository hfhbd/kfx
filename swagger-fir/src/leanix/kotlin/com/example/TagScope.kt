package com.example

import kotlin.String
import kotlinx.serialization.Serializable

/**
 * A combination of tag and tag group.
 */
@Serializable
public data class TagScope(
  /**
   * The group name or the regular expression pattern 
   */
  public val group: String? = null,
  /**
   * The tag name or the regular expression pattern 
   */
  public val tag: String? = null,
)
