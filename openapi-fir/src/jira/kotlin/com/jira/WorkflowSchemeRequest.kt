package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "WorkflowSchemeRequest")
public data class WorkflowSchemeRequest(
  public val defaultWorkflow: Boolean? = null,
  public val issueTypes: List<String> = emptyList(),
  public val workflow: String? = null,
)
