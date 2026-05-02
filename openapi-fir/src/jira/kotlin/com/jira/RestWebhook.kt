package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RestWebhook")
public data class RestWebhook(
  public val active: Boolean? = null,
  public val configuration: Map<String, String> = emptyMap(),
  public val credentials: RestWebhookCredentials? = null,
  public val empty: Boolean? = null,
  public val events: List<String> = emptyList(),
  public val name: String? = null,
  public val scopeType: String? = null,
  public val sslVerificationRequired: Boolean? = null,
  public val statistics: Statistics? = null,
  public val url: String? = null,
)
