package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueIndexSummaryBean")
public data class IssueIndexSummaryBean(
  public val countInArchive: Long? = null,
  public val countInDatabase: Long? = null,
  public val countInIndex: Long? = null,
  public val indexReadable: Boolean? = null,
  public val lastUpdatedInDatabase: Instant? = null,
  public val lastUpdatedInIndex: Instant? = null,
)
