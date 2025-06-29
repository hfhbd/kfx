package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "WorkflowSchemeRequest")
public data class WorkflowSchemeRequest(
  public val defaultWorkflow: Boolean? = null,
  public val issueTypes: List<String>,
  public val workflow: String? = null,
)
