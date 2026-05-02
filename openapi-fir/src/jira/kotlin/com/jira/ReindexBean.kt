package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.time.Instant
import kotlinx.serialization.Serializable

@Serializable
public data class ReindexBean(
  public val currentProgress: Long? = null,
  public val currentSubTask: String? = null,
  public val finishTime: Instant? = null,
  public val progressUrl: String? = null,
  public val startTime: Instant? = null,
  public val submittedTime: Instant? = null,
  public val success: Boolean? = null,
  public val type: ReindexBeanType? = null,
)
