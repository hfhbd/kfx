package com.jira

import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueSubTaskMovePositionBean")
public data class IssueSubTaskMovePositionBean(
  public val current: Long? = null,
  public val original: Long? = null,
)
