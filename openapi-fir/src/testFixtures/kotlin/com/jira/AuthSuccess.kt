package com.jira

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AuthSuccess")
public data class AuthSuccess(
  public val loginInfo: LoginInfo? = null,
  public val session: SessionInfo? = null,
)
