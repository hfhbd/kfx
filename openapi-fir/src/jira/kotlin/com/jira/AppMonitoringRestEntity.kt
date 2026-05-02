package com.jira

import kotlin.Boolean
import kotlinx.serialization.Serializable

@Serializable
public data class AppMonitoringRestEntity(
  public val enabled: Boolean? = null,
)
