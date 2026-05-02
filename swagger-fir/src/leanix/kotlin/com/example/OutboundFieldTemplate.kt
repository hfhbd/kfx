package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class OutboundFieldTemplate(
  /**
   * The expression that is used to evaluate the 'json key' of the data entry
   */
  public val key: KeyTemplate,
  /**
   * Either 'list' or 'selectFirst'. Defines if the field should be written as a list or as a single element.
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val mode: String? = null,
  /**
   * A list of expressions for multiple values, e.g. for multi select fields
   */
  public val values: List<ValueTemplate> = emptyList(),
  /**
   * An EL expression evaluating to a list or a single value. Expands this single value template to a list of value templates available under the JUEL expressions ${integration.updates.keyOfForEach} and ${integration.updates.valueOfForEach}
   */
  public val forEach: ValueForEachTemplate? = null,
  /**
   * Whether this value is optional. If true, then missing value error will not be reported
   */
  public val optional: Boolean? = null,
)
