package com.example

import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "FastRunResponse")
public data class FastRunResponse(
  /**
   * The status result of the run, like FINISHED, FAILED.
   */
  public val status: String? = null,
  /**
   * A list of error/warnings messages found during the execution.
   */
  public val warnings: List<Warning>,
  /**
   * Statistics about the execution of the run.
   */
  public val stats: FastRunStatsReport? = null,
  /**
   * Output ldif when writeToLdif processor is used.
   */
  public val results: Unit? = null,
)
