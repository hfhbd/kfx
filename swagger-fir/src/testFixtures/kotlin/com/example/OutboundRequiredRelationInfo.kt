package com.example

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OutboundRequiredRelationInfo")
public data class OutboundRequiredRelationInfo(
  /**
   * The relation types to filter for
   */
  public val filter: List<String>,
  /**
   * The names of the fields of a relation that are be available
   */
  public val fields: List<String>,
  /**
   * The names of the fields of the target Fact Sheet that are available
   */
  public val targetFields: List<String>,
  /**
   * Whether to get constraining relations of relations
   */
  public val constrainingRelations: Boolean? = null,
)
