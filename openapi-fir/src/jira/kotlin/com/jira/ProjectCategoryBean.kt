package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectCategoryBean")
public data class ProjectCategoryBean(
  public val description: String? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val self: String? = null,
)
