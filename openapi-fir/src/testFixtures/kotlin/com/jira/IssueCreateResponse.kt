package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueCreateResponse")
public data class IssueCreateResponse(
  public val id: String? = null,
  public val key: String? = null,
  public val self: String? = null,
)
