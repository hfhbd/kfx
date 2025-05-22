package com.example

import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class TagInboundProcessorTagInboundProcessor(
  /**
   * A template which is evaluated to represent a unique identifier of a Fact Sheet, or a group of Fact Sheets with certain criteria
   */
  public val identifier: IdentifierWithSearchScopeTemplate? = null,
  /**
   * The Fact Sheets to which the tags are applied
   */
  public val factSheets: IdentifierSetTemplate? = null,
  /**
   * A list of changes that are performed to the specified tag
   */
  public val updates: List<PatchTemplate>,
)
