package com.jira

import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlin.time.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IndexSummaryBean")
public data class IndexSummaryBean(
  public val issueIndex: IssueIndexSummaryBean? = null,
  public val nodeId: String? = null,
  public val replicationQueues: Map<String, IndexReplicationQueueSummaryBean> = emptyMap(),
  public val reportTime: Instant? = null,
)
