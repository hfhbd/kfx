package com.jira

import kotlin.Long
import kotlinx.serialization.Serializable

@Serializable
public data class SprintSwapBean(
  public val sprintToSwapWith: Long? = null,
  public val swap: Long? = null,
)
