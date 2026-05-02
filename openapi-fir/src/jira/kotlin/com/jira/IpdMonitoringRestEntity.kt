package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IpdMonitoringRestEntity")
public data class IpdMonitoringRestEntity(
  public val enabled: Boolean? = null,
)
