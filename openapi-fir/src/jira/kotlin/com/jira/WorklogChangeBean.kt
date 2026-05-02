package com.jira

import kotlin.Long
import kotlinx.serialization.Serializable

@Serializable
public data class WorklogChangeBean(
  public val updatedTime: Long? = null,
  public val worklogId: Long? = null,
)
