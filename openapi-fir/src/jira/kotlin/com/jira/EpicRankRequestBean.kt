package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EpicRankRequestBean")
public data class EpicRankRequestBean(
  public val rankAfterEpic: String? = null,
  public val rankBeforeEpic: String? = null,
  public val rankCustomFieldId: Long? = null,
)
