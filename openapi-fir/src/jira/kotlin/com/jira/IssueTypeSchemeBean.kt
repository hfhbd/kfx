package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueTypeSchemeBean")
public data class IssueTypeSchemeBean(
  public val defaultIssueType: IssueTypeJsonBean? = null,
  public val description: String? = null,
  public val expand: String? = null,
  public val id: String? = null,
  public val issueTypes: List<IssueTypeJsonBean>,
  public val name: String? = null,
  public val self: String? = null,
)
