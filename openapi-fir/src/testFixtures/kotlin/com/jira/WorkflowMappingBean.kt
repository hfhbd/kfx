package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "WorkflowMappingBean")
public data class WorkflowMappingBean(
  public val defaultMapping: Boolean? = null,
  public val issueTypes: List<String>,
  public val updateDraftIfNeeded: Boolean? = null,
  public val workflow: String? = null,
)
