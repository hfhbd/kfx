package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GroupSuggestionsBean")
public data class GroupSuggestionsBean(
  public val groups: List<GroupSuggestionBean>,
  public val `header`: String? = null,
  public val total: Int? = null,
)
