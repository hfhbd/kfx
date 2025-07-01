package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SynchronizationRun")
public data class SynchronizationRun(
  /**
   * A unique ID to distinguish this LDIF instance from other LDIF instances
   */
  public val id: String? = null,
)
