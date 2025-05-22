package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OptionsSettings")
public data class OptionsSettings(
  public val issueContext: IssueContextParam? = null,
  public val options: List<Option>,
)
