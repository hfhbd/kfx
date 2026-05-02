package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class SessionInfo(
  public val name: String? = null,
  public val `value`: String? = null,
)
