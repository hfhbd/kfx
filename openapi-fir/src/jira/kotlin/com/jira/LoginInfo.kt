package com.jira

import kotlin.Long
import kotlin.time.Instant
import kotlinx.serialization.Serializable

@Serializable
public data class LoginInfo(
  public val failedLoginCount: Long? = null,
  public val lastFailedLoginTime: Instant? = null,
  public val loginCount: Long? = null,
  public val previousLoginTime: Instant? = null,
)
