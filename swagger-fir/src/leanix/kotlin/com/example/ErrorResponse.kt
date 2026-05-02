package com.example

import kotlin.Exception
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.Serializable

/**
 * Error response with details in a list
 */
@Serializable
public data class ErrorResponse(
  /**
   * A list of error details
   */
  public val errors: List<String>,
) : Exception()
