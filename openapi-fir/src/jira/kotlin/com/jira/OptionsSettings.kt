package com.jira

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class OptionsSettings(
  public val issueContext: IssueContextParam? = null,
  public val options: List<Option> = emptyList(),
)
