package com.example

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A value template describes the mapping from an incoming object to a single value or a json object
 */
@Serializable
@SerialName(value = "ValueTemplate")
public data class ValueTemplate(
  /**
   * An EL expression that evaluates to a single value. Use 'map' to evaluate to whole json object
   */
  public val expr: String? = null,
  /**
   * If this regex does not match on the evaluated 'expr' then this value template is ignored. Can not be used with 'map'
   */
  public val regexMatch: String? = null,
  /**
   * Allows to modify the evaluated 'expr'. Can not be used with 'map'
   */
  public val regexReplace: RegexReplace? = null,
  /**
   * A list of key-value pairs that evaluates to a json object. Use 'expr' to evaluate to a single value
   */
  public val map: List<KeyValueTemplate>,
  /**
   * An EL expression evaluating to a list or a single value. Expands this single value template to a list of value templates.
   */
  public val forEach: ValueForEachTemplate? = null,
  /**
   * An EL expression that evaluates to an arbitrary data object.
   */
  public val `object`: String? = null,
)
