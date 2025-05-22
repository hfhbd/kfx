package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AuthParams")
public data class AuthParams(
  public val password: String? = null,
  public val username: String? = null,
)
