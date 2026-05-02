package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class VersionUnresolvedIssueCountsBean(
  public val issuesUnresolvedCount: Long? = null,
  public val self: String? = null,
)
