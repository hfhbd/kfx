package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueInvolvementBean")
public data class IssueInvolvementBean(
  public val id: String? = null,
  public val label: String? = null,
)
