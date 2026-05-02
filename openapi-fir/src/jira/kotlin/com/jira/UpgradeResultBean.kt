package com.jira

import kotlin.Long
import kotlin.String
import kotlin.time.Instant
import kotlinx.serialization.Serializable

@Serializable
public data class UpgradeResultBean(
  public val duration: Long? = null,
  public val message: String? = null,
  public val outcome: String? = null,
  public val startTime: Instant? = null,
)
