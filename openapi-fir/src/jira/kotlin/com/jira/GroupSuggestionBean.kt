package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class GroupSuggestionBean(
  public val html: String? = null,
  public val labels: List<GroupLabelBean> = emptyList(),
  public val name: String? = null,
)
