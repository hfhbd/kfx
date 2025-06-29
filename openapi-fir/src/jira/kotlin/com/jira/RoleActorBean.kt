package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RoleActorBean")
public data class RoleActorBean(
  public val avatarUrl: String? = null,
  public val name: String? = null,
)
