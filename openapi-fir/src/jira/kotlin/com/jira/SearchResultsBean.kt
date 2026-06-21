package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.Set
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import kotlin.collections.emptySet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SearchResultsBean")
public data class SearchResultsBean(
  public val expand: String? = null,
  public val issues: List<IssueBean> = emptyList(),
  public val maxResults: Int? = null,
  public val names: Map<String, String> = emptyMap(),
  public val schema: Map<String, JsonTypeBean> = emptyMap(),
  public val startAt: Int? = null,
  public val total: Int? = null,
  public val warningMessages: Set<String>? = emptySet(),
)
