package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueSection")
public data class IssueSection(
  public val id: String? = null,
  public val issues: List<IssuePickerIssue>,
  public val label: String? = null,
  public val msg: String? = null,
  public val sub: String? = null,
)
