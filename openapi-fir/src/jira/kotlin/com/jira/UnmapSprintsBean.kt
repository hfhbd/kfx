package com.jira

import kotlin.Long
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class UnmapSprintsBean(
  public val sprintIds: List<Long> = emptyList(),
)
