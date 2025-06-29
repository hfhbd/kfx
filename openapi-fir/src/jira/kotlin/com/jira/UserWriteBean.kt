package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UserWriteBean")
public data class UserWriteBean(
  public val active: Boolean? = null,
  public val applicationKeys: List<String>,
  public val displayName: String? = null,
  public val emailAddress: String? = null,
  public val key: String? = null,
  public val name: String? = null,
  public val notification: String? = null,
  public val password: String? = null,
  public val self: String? = null,
)
