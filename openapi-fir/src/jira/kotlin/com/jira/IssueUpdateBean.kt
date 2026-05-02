package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class IssueUpdateBean(
  public val fields: JsonObject? = null,
  public val historyMetadata: HistoryMetadata? = null,
  public val properties: List<EntityPropertyBean> = emptyList(),
  public val transition: TransitionBean? = null,
  public val update: Map<String, List<FieldOperation>> = emptyMap(),
)
