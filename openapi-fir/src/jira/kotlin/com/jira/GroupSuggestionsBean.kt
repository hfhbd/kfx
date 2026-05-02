package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class GroupSuggestionsBean(
  public val groups: List<GroupSuggestionBean> = emptyList(),
  public val `header`: String? = null,
  public val total: Int? = null,
)
