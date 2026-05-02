package com.jira

import kotlin.Long
import kotlinx.serialization.Serializable

@Serializable
public data class IssueSubTaskMovePositionBean(
  public val current: Long? = null,
  public val original: Long? = null,
)
