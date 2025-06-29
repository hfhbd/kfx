package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssuesCreateResponse")
public data class IssuesCreateResponse(
  public val errors: List<BulkOperationErrorResult>,
  public val issues: List<IssueCreateResponse>,
)
