package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class Fields(
  public val issuetype: IssueTypeJsonBean? = null,
  public val priority: PriorityJsonBean? = null,
  public val status: StatusJsonBean? = null,
  public val summary: String? = null,
)
