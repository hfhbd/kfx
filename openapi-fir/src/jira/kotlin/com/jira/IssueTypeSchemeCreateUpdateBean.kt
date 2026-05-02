package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueTypeSchemeCreateUpdateBean")
public data class IssueTypeSchemeCreateUpdateBean(
  public val defaultIssueTypeId: String? = null,
  public val description: String? = null,
  public val issueTypeIDs: List<String> = emptyList(),
  public val issueTypeIds: List<String> = emptyList(),
  public val name: String? = null,
)
