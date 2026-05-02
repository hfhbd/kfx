package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "VersionUnresolvedIssueCountsBean")
public data class VersionUnresolvedIssueCountsBean(
  public val issuesUnresolvedCount: Long? = null,
  public val self: String? = null,
)
