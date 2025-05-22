package com.jira

import kotlin.Long
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ReindexRequestBean")
public data class ReindexRequestBean(
  public val completionTime: Instant? = null,
  public val id: Long? = null,
  public val requestTime: Instant? = null,
  public val startTime: Instant? = null,
  public val status: ReindexRequestBeanStatus? = null,
  public val type: ReindexRequestBeanType? = null,
)
