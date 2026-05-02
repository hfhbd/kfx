package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class PageBean(
  public val isLast: Boolean? = null,
  public val maxResults: Int? = null,
  public val nextPage: String? = null,
  public val self: String? = null,
  public val startAt: Long? = null,
  public val total: Long? = null,
  public val values: List<PageBeanValues> = emptyList(),
)
