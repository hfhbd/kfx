package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class AvailableProjectsRequestBean(
  public val ignoredProjectIds: List<String> = emptyList(),
)
