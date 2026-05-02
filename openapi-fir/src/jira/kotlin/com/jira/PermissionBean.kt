package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class PermissionBean(
  public val group: String? = null,
  public val permType: String? = null,
)
