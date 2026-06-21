package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.Set
import kotlin.collections.emptySet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "WorkflowSchemeRequest")
public data class WorkflowSchemeRequest(
  public val defaultWorkflow: Boolean? = null,
  public val issueTypes: Set<String>? = emptySet(),
  public val workflow: String? = null,
)
