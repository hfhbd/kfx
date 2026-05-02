package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class EntityTypeBean(
  public val applicationTypeClassName: String? = null,
  public val i18nKey: String? = null,
  public val iconUrl: String? = null,
  public val pluralizedI18nKey: String? = null,
)
