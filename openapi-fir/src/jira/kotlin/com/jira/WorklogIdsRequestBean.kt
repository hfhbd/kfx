package com.jira

import kotlin.Long
import kotlin.collections.Set
import kotlin.collections.emptySet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "WorklogIdsRequestBean")
public data class WorklogIdsRequestBean(
  /**
   * List of worklog ids
   */
  public val ids: Set<Long>? = emptySet(),
)
