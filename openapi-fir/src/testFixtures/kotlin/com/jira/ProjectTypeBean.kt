package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectTypeBean")
public data class ProjectTypeBean(
  public val color: String? = null,
  public val descriptionI18nKey: String? = null,
  public val formattedKey: String? = null,
  public val icon: String? = null,
  public val key: String? = null,
)
