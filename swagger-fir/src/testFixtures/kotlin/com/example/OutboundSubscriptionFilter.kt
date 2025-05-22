package com.example

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OutboundSubscriptionFilter")
public data class OutboundSubscriptionFilter(
  /**
   * The subscription types to retrieve
   */
  public val types: List<String>,
)
