package com.jira

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class IssueTypeSchemeListBean(
  public val schemes: List<IssueTypeSchemeBean> = emptyList(),
)
