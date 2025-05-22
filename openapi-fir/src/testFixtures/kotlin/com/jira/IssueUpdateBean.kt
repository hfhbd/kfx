package com.jira

import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueUpdateBean")
public data class IssueUpdateBean(
  public val fields: Unit? = null,
  public val historyMetadata: HistoryMetadata? = null,
  public val properties: List<EntityPropertyBean>,
  public val transition: TransitionBean? = null,
  public val update: Unit? = null,
)
