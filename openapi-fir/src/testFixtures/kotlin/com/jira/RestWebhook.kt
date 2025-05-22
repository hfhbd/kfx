package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RestWebhook")
public data class RestWebhook(
  public val active: Boolean? = null,
  public val configuration: Unit? = null,
  public val credentials: RestWebhookCredentials? = null,
  public val empty: Boolean? = null,
  public val events: List<String>,
  public val name: String? = null,
  public val scopeType: String? = null,
  public val sslVerificationRequired: Boolean? = null,
  public val statistics: Statistics? = null,
  public val url: String? = null,
)
