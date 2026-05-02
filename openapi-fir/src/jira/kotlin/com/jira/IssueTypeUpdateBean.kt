package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class IssueTypeUpdateBean(
  public val avatarId: Long? = null,
  public val description: String? = null,
  public val name: String? = null,
)
