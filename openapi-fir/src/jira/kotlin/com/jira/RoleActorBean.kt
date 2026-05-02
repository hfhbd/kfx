package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class RoleActorBean(
  public val avatarUrl: String? = null,
  public val name: String? = null,
)
