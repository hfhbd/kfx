package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UpgradeResultBean")
public data class UpgradeResultBean(
  public val duration: Long? = null,
  public val message: String? = null,
  public val outcome: String? = null,
  public val startTime: Instant? = null,
)
