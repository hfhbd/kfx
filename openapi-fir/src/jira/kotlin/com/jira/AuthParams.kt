package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class AuthParams(
  public val password: String? = null,
  public val username: String? = null,
)
