package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectBean")
public data class ProjectBean(
  public val archived: Boolean? = null,
  public val avatarUrls: Map<String, String>? = null,
  public val description: String? = null,
  public val id: String? = null,
  public val key: String? = null,
  public val name: String? = null,
  public val self: String? = null,
)
