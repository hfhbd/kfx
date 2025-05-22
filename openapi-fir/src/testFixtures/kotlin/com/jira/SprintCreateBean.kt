package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SprintCreateBean")
public data class SprintCreateBean(
  public val autoStartStop: Boolean? = null,
  public val endDate: String? = null,
  public val goal: String? = null,
  public val incompleteIssuesDestinationId: Long? = null,
  public val name: String? = null,
  public val originBoardId: Long? = null,
  public val startDate: String? = null,
  public val synced: Boolean? = null,
  public val userProfileTimeZone: String? = null,
)
