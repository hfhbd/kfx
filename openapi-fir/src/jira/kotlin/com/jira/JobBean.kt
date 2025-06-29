package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "JobBean")
public data class JobBean(
  public val cronExpression: String? = null,
  public val firstRunTime: Long? = null,
  public val intervalInMillis: Long? = null,
  public val jobId: String? = null,
  public val jobRunnerKey: String? = null,
  public val nextRunTime: Long? = null,
  public val runMode: String? = null,
  public val runnable: Boolean? = null,
  public val scheduleType: String? = null,
  public val timeZoneId: String? = null,
)
