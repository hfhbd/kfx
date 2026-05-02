package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class IssueRefJsonBean(
  public val fields: Fields? = null,
  public val id: String? = null,
  public val key: String? = null,
  public val self: String? = null,
)
