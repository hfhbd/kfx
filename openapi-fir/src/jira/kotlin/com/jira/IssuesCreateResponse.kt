package com.jira

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class IssuesCreateResponse(
  public val errors: List<BulkOperationErrorResult> = emptyList(),
  public val issues: List<IssueCreateResponse> = emptyList(),
)
