package com.jira

import kotlin.Long
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class ProjectDeleteInstructionsBean(
  public val grantsToDelete: List<Long> = emptyList(),
)
