package com.jira

import kotlin.Long
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "LoginInfo")
public data class LoginInfo(
  public val failedLoginCount: Long? = null,
  public val lastFailedLoginTime: Instant? = null,
  public val loginCount: Long? = null,
  public val previousLoginTime: Instant? = null,
)
