package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class IssueContextParam(
  public val issueTypeId: String? = null,
  public val projectId: Long? = null,
)
