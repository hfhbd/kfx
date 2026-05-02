package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.time.Instant
import kotlinx.serialization.Serializable

@Serializable
public data class IssueIndexSummaryBean(
  public val countInArchive: Long? = null,
  public val countInDatabase: Long? = null,
  public val countInIndex: Long? = null,
  public val indexReadable: Boolean? = null,
  public val lastUpdatedInDatabase: Instant? = null,
  public val lastUpdatedInIndex: Instant? = null,
)
