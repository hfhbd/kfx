package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RemoveWorkflowRequest")
public data class RemoveWorkflowRequest(
  public val nextDefaultWorkflow: String? = null,
  public val workflow: String? = null,
)
