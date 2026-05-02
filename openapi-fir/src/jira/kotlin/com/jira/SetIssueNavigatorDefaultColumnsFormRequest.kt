package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class SetIssueNavigatorDefaultColumnsFormRequest(
  public val columns: List<String> = emptyList(),
)
