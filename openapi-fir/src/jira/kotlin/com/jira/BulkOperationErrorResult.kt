package com.jira

import kotlin.Int
import kotlinx.serialization.Serializable

@Serializable
public data class BulkOperationErrorResult(
  public val elementErrors: ErrorCollection? = null,
  public val failedElementNumber: Int? = null,
  public val status: Int? = null,
)
