package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SessionInfo")
public data class SessionInfo(
  public val name: String? = null,
  public val `value`: String? = null,
)
