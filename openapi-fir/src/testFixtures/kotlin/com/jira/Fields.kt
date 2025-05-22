package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Fields")
public data class Fields(
  public val issuetype: IssueTypeJsonBean? = null,
  public val priority: PriorityJsonBean? = null,
  public val status: StatusJsonBean? = null,
  public val summary: String? = null,
)
