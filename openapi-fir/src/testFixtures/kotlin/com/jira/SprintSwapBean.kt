package com.jira

import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SprintSwapBean")
public data class SprintSwapBean(
  public val sprintToSwapWith: Long? = null,
  public val swap: Long? = null,
)
