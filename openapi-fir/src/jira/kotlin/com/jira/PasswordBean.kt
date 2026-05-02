package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class PasswordBean(
  public val currentPassword: String? = null,
  public val password: String? = null,
)
