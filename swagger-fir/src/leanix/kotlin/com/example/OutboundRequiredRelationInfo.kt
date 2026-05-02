package com.example

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class OutboundRequiredRelationInfo(
  /**
   * The relation types to filter for
   */
  public val filter: List<String> = emptyList(),
  /**
   * The names of the fields of a relation that are be available
   */
  public val fields: List<String> = emptyList(),
  /**
   * The names of the fields of the target Fact Sheet that are available
   */
  public val targetFields: List<String> = emptyList(),
  /**
   * Whether to get constraining relations of relations
   */
  public val constrainingRelations: Boolean? = null,
)
