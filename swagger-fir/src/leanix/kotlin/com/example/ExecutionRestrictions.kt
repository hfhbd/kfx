package com.example

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

/**
 * When used only the specified users or the default technical user can execute the processor
 */
@Serializable
public data class ExecutionRestrictions(
  /**
   * Execution is restricted to the default technical user
   */
  public val defaultTechnicalUser: Boolean? = null,
  /**
   * Execution is restricted to the specified users
   */
  public val userIds: List<String> = emptyList(),
)
