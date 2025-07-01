package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueTypeMappingBean")
public data class IssueTypeMappingBean(
  public val issueType: String? = null,
  public val updateDraftIfNeeded: Boolean? = null,
  public val workflow: String? = null,
)
