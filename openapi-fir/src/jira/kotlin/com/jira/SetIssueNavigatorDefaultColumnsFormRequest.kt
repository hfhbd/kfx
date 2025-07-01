package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SetIssueNavigatorDefaultColumnsFormRequest")
public data class SetIssueNavigatorDefaultColumnsFormRequest(
  public val columns: List<String>,
)
