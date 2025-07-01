package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OpsbarBean")
public data class OpsbarBean(
  public val linkGroups: List<LinkGroupBean>,
)
