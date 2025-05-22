package com.example

import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class SubscriptionInboundProcessorSubscriptionInboundProcessor(
  /**
   * A template which is evaluated to represent a unique identifier of a Fact Sheet, or a group of Fact Sheets with certain criteria
   */
  public val identifier: IdentifierWithSearchScopeTemplate,
  /**
   * A list of changes that are performed to the specified subscription
   */
  public val updates: List<PatchTemplate>,
)
