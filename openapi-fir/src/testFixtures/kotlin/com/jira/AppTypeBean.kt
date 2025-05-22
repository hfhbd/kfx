package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AppTypeBean")
public data class AppTypeBean(
  public val i18nKey: String? = null,
  public val iconUrl: String? = null,
)
