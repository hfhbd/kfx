package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class EpicRankRequestBean(
  public val rankAfterEpic: String? = null,
  public val rankBeforeEpic: String? = null,
  public val rankCustomFieldId: Long? = null,
)
