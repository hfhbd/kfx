package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AppMonitoringRestEntity")
public data class AppMonitoringRestEntity(
  public val enabled: Boolean? = null,
)
