package com.jira

import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SearchResultsBean")
public data class SearchResultsBean(
  public val expand: String? = null,
  public val issues: List<IssueBean>,
  public val maxResults: Int? = null,
  public val names: Unit? = null,
  public val schema: Unit? = null,
  public val startAt: Int? = null,
  public val total: Int? = null,
  public val warningMessages: List<String>,
)
