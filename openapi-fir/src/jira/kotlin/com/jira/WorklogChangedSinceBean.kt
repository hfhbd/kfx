package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class WorklogChangedSinceBean(
  public val isLastPage: Boolean? = null,
  public val lastPage: Boolean? = null,
  public val nextPage: String? = null,
  public val self: String? = null,
  public val since: Long? = null,
  public val until: Long? = null,
  public val values: List<WorklogChangeBean> = emptyList(),
)
