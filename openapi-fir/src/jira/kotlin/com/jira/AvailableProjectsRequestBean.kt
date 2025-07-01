package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AvailableProjectsRequestBean")
public data class AvailableProjectsRequestBean(
  public val ignoredProjectIds: List<String>,
)
