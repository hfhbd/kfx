package com.jira

import kotlin.Long
import kotlinx.serialization.Serializable

@Serializable
public data class RankingConfigBean(
  public val rankCustomFieldId: Long? = null,
)
