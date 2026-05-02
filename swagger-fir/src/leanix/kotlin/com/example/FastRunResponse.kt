package com.example

import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class FastRunResponse(
  /**
   * The status result of the run, like FINISHED, FAILED.
   */
  public val status: String? = null,
  /**
   * A list of error/warnings messages found during the execution.
   */
  public val warnings: List<Warning> = emptyList(),
  /**
   * Statistics about the execution of the run.
   */
  public val stats: FastRunStatsReport? = null,
  /**
   * Output ldif when writeToLdif processor is used.
   */
  public val results: Unit? = null,
)
