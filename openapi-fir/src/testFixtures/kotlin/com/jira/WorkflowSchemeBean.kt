package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "WorkflowSchemeBean")
public data class WorkflowSchemeBean(
  public val defaultWorkflow: String? = null,
  public val description: String? = null,
  public val draft: Boolean? = null,
  public val id: Long? = null,
  public val issueTypeMappings: Unit? = null,
  public val issueTypes: Unit? = null,
  public val lastModified: String? = null,
  public val lastModifiedUser: UserBean? = null,
  public val name: String? = null,
  public val originalDefaultWorkflow: String? = null,
  public val originalIssueTypeMappings: Unit? = null,
  public val self: String? = null,
  public val updateDraftIfNeeded: Boolean? = null,
)
