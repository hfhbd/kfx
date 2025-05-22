package com.example

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class OutboundInputOutboundInput(
  /**
   * The type of connector that is used
   */
  public val connectorType: String,
  /**
   * The identifier of the connector instance
   */
  public val connectorId: String,
  /**
   * The version of the connector that is expected to process this LDIF file
   */
  public val connectorVersion: String,
  /**
   * The direction of the data flow.
   */
  public val processingDirection: OutboundInputOutboundInputProcessingDirection,
  /**
   * Optional additional options to parse this LDIF request
   */
  public val processingMode: OutboundInputOutboundInputProcessingMode? = null,
  /**
   * A customer added, arbitrary description for any kind of grouping, notification or note purpose
   */
  public val description: String? = null,
)
