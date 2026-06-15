package com.jira

import kotlin.String
import kotlin.collections.Set
import kotlin.collections.emptySet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AvailableProjectsRequestBean")
public data class AvailableProjectsRequestBean(
  public val ignoredProjectIds: Set<String>? = emptySet(),
)
