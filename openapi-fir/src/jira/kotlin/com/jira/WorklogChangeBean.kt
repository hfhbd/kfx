package com.jira

import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "WorklogChangeBean")
public data class WorklogChangeBean(
  public val updatedTime: Long? = null,
  public val worklogId: Long? = null,
)
