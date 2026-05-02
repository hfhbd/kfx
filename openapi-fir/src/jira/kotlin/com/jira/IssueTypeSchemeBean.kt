package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class IssueTypeSchemeBean(
  public val defaultIssueType: IssueTypeJsonBean? = null,
  public val description: String? = null,
  public val expand: String? = null,
  public val id: String? = null,
  public val issueTypes: List<IssueTypeJsonBean> = emptyList(),
  public val name: String? = null,
  public val self: String? = null,
)
