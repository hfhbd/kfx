package com.example

import kotlin.Int
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Processor")
public data class Processor(
  /**
   * The name of the processor as defined by the processor configuration
   */
  public val name: String? = null,
  /**
   * An internal integer value used to identify the processor
   */
  public val index: Int? = null,
)
