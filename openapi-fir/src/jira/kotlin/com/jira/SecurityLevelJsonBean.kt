package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class SecurityLevelJsonBean(
  public val description: String? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val self: String? = null,
)
