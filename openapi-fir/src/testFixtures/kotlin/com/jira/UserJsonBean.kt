package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UserJsonBean")
public data class UserJsonBean(
  public val active: Boolean? = null,
  public val avatarUrls: Unit? = null,
  public val displayName: String? = null,
  public val emailAddress: String? = null,
  public val key: String? = null,
  public val name: String? = null,
  public val self: String? = null,
  public val timeZone: String? = null,
)
