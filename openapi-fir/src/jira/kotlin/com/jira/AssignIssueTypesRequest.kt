package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AssignIssueTypesRequest")
public data class AssignIssueTypesRequest(
  public val issueTypes: List<String> = emptyList(),
  public val name: String? = null,
  public val valid: Boolean? = null,
)
