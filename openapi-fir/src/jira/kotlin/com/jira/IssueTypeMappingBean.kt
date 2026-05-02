package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class IssueTypeMappingBean(
  public val issueType: String? = null,
  public val updateDraftIfNeeded: Boolean? = null,
  public val workflow: String? = null,
)
