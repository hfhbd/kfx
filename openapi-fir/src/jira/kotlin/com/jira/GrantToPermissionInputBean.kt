package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class GrantToPermissionInputBean(
  public val securityType: String? = null,
  public val `value`: OptionString? = null,
)
