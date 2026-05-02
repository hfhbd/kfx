package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class IssueTypeWithStatusJsonBean(
  public val id: String? = null,
  public val name: String? = null,
  public val self: String? = null,
  public val statuses: List<StatusJsonBean> = emptyList(),
  public val subtask: Boolean? = null,
)
