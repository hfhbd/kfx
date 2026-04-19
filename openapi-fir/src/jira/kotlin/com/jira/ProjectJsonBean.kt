package com.jira

import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectJsonBean")
public data class ProjectJsonBean(
  public val avatarUrls: Map<String, String> = emptyMap(),
  public val id: String? = null,
  public val key: String? = null,
  public val name: String? = null,
  public val projectCategory: ProjectCategoryJsonBean? = null,
  public val projectTypeKey: String? = null,
  public val self: String? = null,
)
