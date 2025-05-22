package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ConfigurationBean")
public data class ConfigurationBean(
  public val attachmentsEnabled: Boolean? = null,
  public val issueLinkingEnabled: Boolean? = null,
  public val subTasksEnabled: Boolean? = null,
  public val timeTrackingConfiguration: TimeTrackingConfigurationBean? = null,
  public val timeTrackingEnabled: Boolean? = null,
  public val unassignedIssuesAllowed: Boolean? = null,
  public val votingEnabled: Boolean? = null,
  public val watchingEnabled: Boolean? = null,
)
