package com.jira

import kotlin.Boolean
import kotlinx.serialization.Serializable

@Serializable
public data class DarkFeaturePropertyBean(
  public val enabled: Boolean? = null,
)
