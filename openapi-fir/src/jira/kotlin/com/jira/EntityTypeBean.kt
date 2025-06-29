package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EntityTypeBean")
public data class EntityTypeBean(
  public val applicationTypeClassName: String? = null,
  public val i18nKey: String? = null,
  public val iconUrl: String? = null,
  public val pluralizedI18nKey: String? = null,
)
