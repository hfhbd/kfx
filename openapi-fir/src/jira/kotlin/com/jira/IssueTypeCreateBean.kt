package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class IssueTypeCreateBean(
  public val description: String? = null,
  public val name: String? = null,
  public val type: IssueTypeCreateBeanType? = null,
)
