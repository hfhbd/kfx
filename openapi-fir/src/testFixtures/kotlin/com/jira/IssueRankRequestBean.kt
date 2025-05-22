package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueRankRequestBean")
public data class IssueRankRequestBean(
  public val issues: List<String>,
  public val rankAfterIssue: String? = null,
  public val rankBeforeIssue: String? = null,
  public val rankCustomFieldId: Long? = null,
)
