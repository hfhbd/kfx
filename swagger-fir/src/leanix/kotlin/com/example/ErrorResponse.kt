package com.example

import kotlin.Exception
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Error response with details in a list
 */
@Serializable
@SerialName(value = "ErrorResponse")
public data class ErrorResponse(
  /**
   * A list of error details
   */
  public val errors: List<String>,
) : Exception()
