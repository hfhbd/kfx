package com.jira

import kotlin.Long
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectDeleteInstructionsBean")
public data class ProjectDeleteInstructionsBean(
  public val grantsToDelete: List<Long>,
)
