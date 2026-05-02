package com.jira

import kotlin.Long
import kotlin.time.Instant
import kotlinx.serialization.Serializable

@Serializable
public data class IndexReplicationQueueEntryBean(
  public val id: Long? = null,
  public val replicationTime: Instant? = null,
)
