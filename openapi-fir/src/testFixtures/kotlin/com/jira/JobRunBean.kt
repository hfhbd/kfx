package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "JobRunBean")
public data class JobRunBean(
  public val durationInMillis: Long? = null,
  public val message: String? = null,
  public val runOutcome: String? = null,
  public val startTime: Instant? = null,
)
