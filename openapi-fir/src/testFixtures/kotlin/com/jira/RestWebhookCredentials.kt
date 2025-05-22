package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RestWebhookCredentials")
public data class RestWebhookCredentials(
  public val empty: Boolean? = null,
  public val password: String? = null,
  public val username: String? = null,
)
