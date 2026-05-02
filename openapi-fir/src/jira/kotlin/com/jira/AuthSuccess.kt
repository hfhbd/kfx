package com.jira

import kotlinx.serialization.Serializable

@Serializable
public data class AuthSuccess(
  public val loginInfo: LoginInfo? = null,
  public val session: SessionInfo? = null,
)
