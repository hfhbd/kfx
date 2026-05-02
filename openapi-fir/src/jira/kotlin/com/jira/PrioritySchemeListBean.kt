package com.jira

import kotlin.Int
import kotlin.Long
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class PrioritySchemeListBean(
  public val maxResults: Int? = null,
  public val schemes: List<PrioritySchemeBean> = emptyList(),
  public val startAt: Long? = null,
  public val total: Int? = null,
)
