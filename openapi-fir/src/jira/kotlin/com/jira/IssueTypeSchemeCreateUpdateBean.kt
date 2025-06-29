package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueTypeSchemeCreateUpdateBean")
public data class IssueTypeSchemeCreateUpdateBean(
  public val defaultIssueTypeId: String? = null,
  public val description: String? = null,
  public val issueTypeIDs: List<String>,
  public val issueTypeIds: List<String>,
  public val name: String? = null,
)
