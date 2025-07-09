package com.jira

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssuesUpdateBean")
public data class IssuesUpdateBean(
  public val issueUpdates: List<IssueUpdateBean> = emptyList(),
)
