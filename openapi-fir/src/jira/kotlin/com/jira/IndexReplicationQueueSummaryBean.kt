package com.jira

import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IndexReplicationQueueSummaryBean")
public data class IndexReplicationQueueSummaryBean(
  public val lastConsumedOperation: IndexReplicationQueueEntryBean? = null,
  public val lastOperationInQueue: IndexReplicationQueueEntryBean? = null,
  public val queueSize: Long? = null,
)
