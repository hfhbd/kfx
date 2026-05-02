package com.example

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class SynchronizationRun(
  /**
   * A unique ID to distinguish this LDIF instance from other LDIF instances
   */
  public val id: String? = null,
)
