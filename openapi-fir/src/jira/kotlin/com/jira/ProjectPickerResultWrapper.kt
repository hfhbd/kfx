package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class ProjectPickerResultWrapper(
  public val `header`: String? = null,
  public val projects: List<ProjectPickerItem> = emptyList(),
  public val total: Int? = null,
)
