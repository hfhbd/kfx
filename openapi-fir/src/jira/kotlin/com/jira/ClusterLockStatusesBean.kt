package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ClusterLockStatusesBean")
public data class ClusterLockStatusesBean(
  public val clusterLocks: List<ClusterLockStatusBean>,
)
