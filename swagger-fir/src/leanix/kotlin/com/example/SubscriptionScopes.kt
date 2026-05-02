package com.example

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

/**
 * The criteria to select subscriptions
 */
@Serializable
public data class SubscriptionScopes(
  /**
   * List of role names
   */
  public val roles: List<String> = emptyList(),
  /**
   * Type of role, like RESPONSIBLE
   */
  public val type: String? = null,
)
