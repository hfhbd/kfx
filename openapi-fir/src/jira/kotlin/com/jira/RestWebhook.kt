package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.Map
import kotlin.collections.Set
import kotlin.collections.emptyMap
import kotlin.collections.emptySet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RestWebhook")
public data class RestWebhook(
  public val active: Boolean? = null,
  public val configuration: Map<String, String> = emptyMap(),
  public val credentials: RestWebhookCredentials? = null,
  public val empty: Boolean? = null,
  public val events: Set<String>? = emptySet(),
  public val name: String? = null,
  public val scopeType: String? = null,
  public val sslVerificationRequired: Boolean? = null,
  public val statistics: Statistics? = null,
  public val url: String? = null,
)
