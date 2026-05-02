package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GrantToPermissionInputBean")
public data class GrantToPermissionInputBean(
  public val securityType: String? = null,
  public val `value`: OptionString? = null,
)
