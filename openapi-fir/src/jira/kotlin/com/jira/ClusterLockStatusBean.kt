package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ClusterLockStatusBean(
  public val holdingLockSec: String? = null,
  public val lockName: String? = null,
  public val lockedByNode: String? = null,
  public val updateTime: String? = null,
)
