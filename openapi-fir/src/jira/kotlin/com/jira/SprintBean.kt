package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SprintBean")
public data class SprintBean(
  public val activatedDate: String? = null,
  public val autoStartStop: Boolean? = null,
  public val completeDate: String? = null,
  public val endDate: String? = null,
  public val goal: String? = null,
  public val id: Long? = null,
  public val incompleteIssuesDestinationId: Long? = null,
  public val name: String? = null,
  public val originBoardId: Long? = null,
  public val self: String? = null,
  public val startDate: String? = null,
  public val state: String? = null,
  public val synced: Boolean? = null,
)
