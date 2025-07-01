package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueUpdateBean")
public data class IssueUpdateBean(
  public val fields: Map<String, Fields>? = null,
  public val historyMetadata: HistoryMetadata? = null,
  public val properties: List<EntityPropertyBean>,
  public val transition: TransitionBean? = null,
  public val update: Map<String, List<FieldOperation>>? = null,
)
