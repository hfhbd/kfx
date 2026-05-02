package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ComponentIssueCountsBean")
public data class ComponentIssueCountsBean(
  public val issueCount: Long? = null,
  public val self: String? = null,
)
