package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SearchResultsBean")
public data class SearchResultsBean(
  public val expand: String? = null,
  public val issues: List<IssueBean>,
  public val maxResults: Int? = null,
  public val names: Map<String, String>? = null,
  public val schema: Map<String, JsonTypeBean>? = null,
  public val startAt: Int? = null,
  public val total: Int? = null,
  public val warningMessages: List<String>,
)
