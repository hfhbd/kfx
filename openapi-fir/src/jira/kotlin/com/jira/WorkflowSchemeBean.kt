package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable

@Serializable
public data class WorkflowSchemeBean(
  public val defaultWorkflow: String? = null,
  public val description: String? = null,
  public val draft: Boolean? = null,
  public val id: Long? = null,
  public val issueTypeMappings: Map<String, String> = emptyMap(),
  public val issueTypes: Map<String, IssueTypeJsonBean> = emptyMap(),
  public val lastModified: String? = null,
  public val lastModifiedUser: UserBean? = null,
  public val name: String? = null,
  public val originalDefaultWorkflow: String? = null,
  public val originalIssueTypeMappings: Map<String, String> = emptyMap(),
  public val self: String? = null,
  public val updateDraftIfNeeded: Boolean? = null,
)
