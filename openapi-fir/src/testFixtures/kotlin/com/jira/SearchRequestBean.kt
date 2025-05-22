package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SearchRequestBean")
public data class SearchRequestBean(
  public val expand: List<String>,
  public val fields: List<String>,
  public val jql: String? = null,
  public val maxResults: Int? = null,
  public val startAt: Int? = null,
  public val validateQuery: Boolean? = null,
)
