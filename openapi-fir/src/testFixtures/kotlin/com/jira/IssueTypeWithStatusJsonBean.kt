package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueTypeWithStatusJsonBean")
public data class IssueTypeWithStatusJsonBean(
  public val id: String? = null,
  public val name: String? = null,
  public val self: String? = null,
  public val statuses: List<StatusJsonBean>,
  public val subtask: Boolean? = null,
)
