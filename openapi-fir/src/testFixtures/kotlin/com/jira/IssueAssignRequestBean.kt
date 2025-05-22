package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueAssignRequestBean")
public data class IssueAssignRequestBean(
  public val issues: List<String>,
)
