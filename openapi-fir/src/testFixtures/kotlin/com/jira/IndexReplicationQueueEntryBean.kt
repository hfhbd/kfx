package com.jira

import kotlin.Long
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IndexReplicationQueueEntryBean")
public data class IndexReplicationQueueEntryBean(
  public val id: Long? = null,
  public val replicationTime: Instant? = null,
)
