package com.jira

import kotlin.Long
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class WorklogIdsRequestBean(
  /**
   * List of worklog ids
   */
  public val ids: List<Long> = emptyList(),
)
