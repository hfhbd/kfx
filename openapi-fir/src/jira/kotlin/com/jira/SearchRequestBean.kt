package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class SearchRequestBean(
  public val expand: List<String> = emptyList(),
  public val fields: List<String> = emptyList(),
  public val jql: String? = null,
  public val maxResults: Int? = null,
  public val startAt: Int? = null,
  public val validateQuery: Boolean? = null,
)
