package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AvailableIssueTypesRequestBean")
public data class AvailableIssueTypesRequestBean(
  public val ignoredIssueTypeIds: List<String>,
)
