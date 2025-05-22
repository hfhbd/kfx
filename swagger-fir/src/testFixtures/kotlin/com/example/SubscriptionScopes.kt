package com.example

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The criteria to select subscriptions
 */
@Serializable
@SerialName(value = "SubscriptionScopes")
public data class SubscriptionScopes(
  /**
   * List of role names
   */
  public val roles: List<String>,
  /**
   * Type of role, like RESPONSIBLE
   */
  public val type: String? = null,
)
