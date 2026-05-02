package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "StatusResponse")
public data class StatusResponse(
  /**
   * The ID of the synchronization run
   */
  public val id: String,
  /**
   * The status of the synchronization run
   */
  public val status: StatusResponseStatus? = null,
  /**
   * The optional description that was provided as part of the Input object.
   */
  public val description: String? = null,
)
