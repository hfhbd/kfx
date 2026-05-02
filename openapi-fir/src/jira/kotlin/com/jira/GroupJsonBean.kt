package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class GroupJsonBean(
  public val name: String? = null,
  public val self: String? = null,
)
