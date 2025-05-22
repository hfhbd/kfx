package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PasswordBean")
public data class PasswordBean(
  public val currentPassword: String? = null,
  public val password: String? = null,
)
