package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DarkFeaturePropertyBean")
public data class DarkFeaturePropertyBean(
  public val enabled: Boolean? = null,
)
