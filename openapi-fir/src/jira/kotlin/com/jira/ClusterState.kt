package com.jira

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ClusterState")
public data class ClusterState(
  public val build: NodeBuildInfo? = null,
  public val state: ClusterStateState? = null,
)
