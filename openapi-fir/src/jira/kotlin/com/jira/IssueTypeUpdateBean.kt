package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueTypeUpdateBean")
public data class IssueTypeUpdateBean(
  public val avatarId: Long? = null,
  public val description: String? = null,
  public val name: String? = null,
)
