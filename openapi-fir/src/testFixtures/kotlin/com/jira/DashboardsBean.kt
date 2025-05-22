package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DashboardsBean")
public data class DashboardsBean(
  public val dashboards: List<DashboardBean>,
  public val maxResults: Int? = null,
  public val next: String? = null,
  public val prev: String? = null,
  public val startAt: Int? = null,
  public val total: Int? = null,
)
