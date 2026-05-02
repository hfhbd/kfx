package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class WorkflowMappingBean(
  public val defaultMapping: Boolean? = null,
  public val issueTypes: List<String> = emptyList(),
  public val updateDraftIfNeeded: Boolean? = null,
  public val workflow: String? = null,
)
