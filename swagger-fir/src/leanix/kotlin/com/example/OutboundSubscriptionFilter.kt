package com.example

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class OutboundSubscriptionFilter(
  /**
   * The subscription types to retrieve
   */
  public val types: List<String> = emptyList(),
)
