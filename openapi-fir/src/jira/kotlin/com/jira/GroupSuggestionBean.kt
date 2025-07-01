package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GroupSuggestionBean")
public data class GroupSuggestionBean(
  public val html: String? = null,
  public val labels: List<GroupLabelBean>,
  public val name: String? = null,
)
