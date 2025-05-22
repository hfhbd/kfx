package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueTypeCreateBean")
public data class IssueTypeCreateBean(
  public val description: String? = null,
  public val name: String? = null,
  public val type: IssueTypeCreateBeanType? = null,
)
