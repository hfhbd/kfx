package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "NodeBean")
public data class NodeBean(
  public val alive: Boolean? = null,
  public val cacheListenerPort: Long? = null,
  public val ip: String? = null,
  public val lastStateChangeTimestamp: Long? = null,
  public val nodeBuildNumber: Long? = null,
  public val nodeId: String? = null,
  public val nodeVersion: String? = null,
  public val state: NodeBeanState? = null,
)
