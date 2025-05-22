package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "WorklogWithPaginationBean")
public data class WorklogWithPaginationBean(
  public val maxResults: Int? = null,
  public val startAt: Int? = null,
  public val total: Int? = null,
  public val worklogs: List<Worklog>,
)
