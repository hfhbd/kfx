package com.jira

import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RankingConfigBean")
public data class RankingConfigBean(
  public val rankCustomFieldId: Long? = null,
)
