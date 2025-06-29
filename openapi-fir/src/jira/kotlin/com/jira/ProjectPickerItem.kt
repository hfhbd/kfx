package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectPickerItem")
public data class ProjectPickerItem(
  public val avatar: String? = null,
  public val html: String? = null,
  public val id: String? = null,
  public val key: String? = null,
  public val name: String? = null,
)
