package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SecurityLevelJsonBean")
public data class SecurityLevelJsonBean(
  public val description: String? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val self: String? = null,
)
