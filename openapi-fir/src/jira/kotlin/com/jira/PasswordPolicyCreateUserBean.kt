package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class PasswordPolicyCreateUserBean(
  public val displayName: String? = null,
  public val emailAddress: String? = null,
  public val password: String? = null,
  public val username: String? = null,
)
