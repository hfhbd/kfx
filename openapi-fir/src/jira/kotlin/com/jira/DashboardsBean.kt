package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class DashboardsBean(
  public val dashboards: List<DashboardBean> = emptyList(),
  public val maxResults: Int? = null,
  public val next: String? = null,
  public val prev: String? = null,
  public val startAt: Int? = null,
  public val total: Int? = null,
)
