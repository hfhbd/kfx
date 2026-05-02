package com.example

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class SynchronizationRunWithConfiguration(
  /**
   * A unique ID to distinguish this LDIF instance from other LDIF instances
   */
  public val id: String? = null,
  /**
   * The processor configuration associated with this synchronization run
   */
  public val processorConfiguration: ProcessorConfiguration? = null,
)
