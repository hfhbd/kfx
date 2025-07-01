package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The criteria to select subscriptions
 */
@Serializable
@SerialName(value = "SubscriptionDeletionScope")
public data class SubscriptionDeletionScope(
  /**
   * The combinations of type and roles for subscriptions
   */
  public val subscriptionScopes: List<SubscriptionScopes>,
  /**
   * The scope for fact sheets where subscriptions are taken from
   */
  public val scope: Scope? = null,
  /**
   * An EL expression to describe the advanced filter settings.
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val advanced: String? = null,
)
