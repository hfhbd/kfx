package com.jira

import kotlinx.serialization.Serializable

@Serializable
public data class ClusterState(
  public val build: NodeBuildInfo? = null,
  public val state: ClusterStateState? = null,
)
