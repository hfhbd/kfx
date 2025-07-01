package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionBean")
public data class PermissionBean(
  public val group: String? = null,
  public val permType: String? = null,
)
