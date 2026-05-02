package com.jira

import kotlin.Long
import kotlin.String
import kotlin.time.Instant
import kotlinx.serialization.Serializable

@Serializable
public data class JobRunBean(
  public val durationInMillis: Long? = null,
  public val message: String? = null,
  public val runOutcome: String? = null,
  public val startTime: Instant? = null,
)
