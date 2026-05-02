package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class RemoveWorkflowRequest(
  public val nextDefaultWorkflow: String? = null,
  public val workflow: String? = null,
)
