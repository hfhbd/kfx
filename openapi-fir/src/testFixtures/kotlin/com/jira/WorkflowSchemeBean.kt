package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "WorkflowSchemeBean")
public data class WorkflowSchemeBean(
  public val defaultWorkflow: String? = null,
  public val description: String? = null,
  public val draft: Boolean? = null,
  public val id: Long? = null,
  public val issueTypeMappings: Map<String, String>? = null,
  public val issueTypes: Map<String, IssueTypeJsonBean>? = null,
  public val lastModified: String? = null,
  public val lastModifiedUser: UserBean? = null,
  public val name: String? = null,
  public val originalDefaultWorkflow: String? = null,
  public val originalIssueTypeMappings: Map<String, String>? = null,
  public val self: String? = null,
  public val updateDraftIfNeeded: Boolean? = null,
)
