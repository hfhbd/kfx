package com.jira

import kotlin.Int
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "BulkOperationErrorResult")
public data class BulkOperationErrorResult(
  public val elementErrors: ErrorCollection? = null,
  public val failedElementNumber: Int? = null,
  public val status: Int? = null,
)
