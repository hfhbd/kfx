package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class NodeBuildInfo(
  public val buildNumber: Long? = null,
  public val version: String? = null,
)
