package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectPickerResultWrapper")
public data class ProjectPickerResultWrapper(
  public val `header`: String? = null,
  public val projects: List<ProjectPickerItem>,
  public val total: Int? = null,
)
