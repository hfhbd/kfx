package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PasswordPolicyUpdateUserBean")
public data class PasswordPolicyUpdateUserBean(
  public val newPassword: String? = null,
  public val oldPassword: String? = null,
  public val username: String? = null,
)
