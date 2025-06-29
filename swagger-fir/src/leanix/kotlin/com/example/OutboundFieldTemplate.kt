package com.example

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OutboundFieldTemplate")
public data class OutboundFieldTemplate(
  /**
   * The expression that is used to evaluate the 'json key' of the data entry
   */
  public val key: KeyTemplate,
  /**
   * Either 'list' or 'selectFirst'. Defines if the field should be written as a list or as a single element.
   */
  public val mode: String? = null,
  /**
   * A list of expressions for multiple values, e.g. for multi select fields
   */
  public val values: List<ValueTemplate>,
  /**
   * An EL expression evaluating to a list or a single value. Expands this single value template to a list of value templates available under the JUEL expressions ${integration.updates.keyOfForEach} and ${integration.updates.valueOfForEach}
   */
  public val forEach: ValueForEachTemplate? = null,
  /**
   * Whether this value is optional. If true, then missing value error will not be reported
   */
  public val optional: Boolean? = null,
)
