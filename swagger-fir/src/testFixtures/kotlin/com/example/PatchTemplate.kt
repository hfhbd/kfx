package com.example

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The template of a key/value pair that defines an abstract change to the data
 */
@Serializable
@SerialName(value = "PatchTemplate")
public data class PatchTemplate(
  /**
   * The expression that is used to evaluate the 'json key' of the data entry
   */
  public val key: KeyTemplate,
  /**
   * Defines the operation that is used. Data can be added or updated
   */
  public val op: String? = null,
  /**
   * A list of expressions for multiple values, e.g. for multi select fields
   */
  public val values: List<ValueTemplate>,
  /**
   * Whether this value is optional. If true, then missing value error will not be reported
   */
  public val optional: Boolean? = null,
  /**
   * An EL expression evaluating to a list or a single value. Expands this single value template to a list of value templates available under the JUEL expressions ${integration.updates.keyOfForEach} and ${integration.updates.valueOfForEach}
   */
  public val forEach: ValueForEachTemplate? = null,
)
