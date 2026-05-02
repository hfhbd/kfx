package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "VersionIssueCountsBean")
public data class VersionIssueCountsBean(
  public val customFieldNames: List<VersionUsageInCustomFields> = emptyList(),
  public val issueCountWithCustomFieldsShowingVersion: Long? = null,
  public val issuesAffectedCount: Long? = null,
  public val issuesFixedCount: Long? = null,
  public val self: String? = null,
)
