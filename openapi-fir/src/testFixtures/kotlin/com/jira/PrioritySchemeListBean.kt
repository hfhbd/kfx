package com.jira

import kotlin.Int
import kotlin.Long
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PrioritySchemeListBean")
public data class PrioritySchemeListBean(
  public val maxResults: Int? = null,
  public val schemes: List<PrioritySchemeBean>,
  public val startAt: Long? = null,
  public val total: Int? = null,
)
