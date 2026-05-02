package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class IssueRankRequestBean(
  public val issues: List<String> = emptyList(),
  public val rankAfterIssue: String? = null,
  public val rankBeforeIssue: String? = null,
  public val rankCustomFieldId: Long? = null,
)
