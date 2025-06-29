package com.example

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SubscriptionFilter")
public data class SubscriptionFilter(
  /**
   * The subscription types to retrieve
   */
  public val types: List<String>,
)
