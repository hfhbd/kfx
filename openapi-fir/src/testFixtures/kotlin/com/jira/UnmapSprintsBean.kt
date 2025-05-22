package com.jira

import kotlin.Long
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UnmapSprintsBean")
public data class UnmapSprintsBean(
  public val sprintIds: List<Long>,
)
