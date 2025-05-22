package com.jira

import kotlin.String
import kotlin.Unit
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IndexSummaryBean")
public data class IndexSummaryBean(
  public val issueIndex: IssueIndexSummaryBean? = null,
  public val nodeId: String? = null,
  public val replicationQueues: Unit? = null,
  public val reportTime: Instant? = null,
)
