package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class PasswordPolicyUpdateUserBean(
  public val newPassword: String? = null,
  public val oldPassword: String? = null,
  public val username: String? = null,
)
