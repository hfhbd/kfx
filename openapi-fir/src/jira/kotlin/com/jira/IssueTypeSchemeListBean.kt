package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueTypeSchemeListBean")
public data class IssueTypeSchemeListBean(
  public val schemes: List<IssueTypeSchemeBean>,
)
