package com.jira

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class ClusterLockStatusesBean(
  public val clusterLocks: List<ClusterLockStatusBean> = emptyList(),
)
