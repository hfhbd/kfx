package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OutboundInput")
public data class OutboundInput(
  /**
   * The type of connector that is used
   */
  public override val connectorType: String,
  /**
   * The identifier of the connector instance
   */
  public override val connectorId: String,
  /**
   * The version of the connector that is expected to process this LDIF file
   */
  public override val connectorVersion: String,
  /**
   * Optional additional options to parse this LDIF request
   */
  public override val processingMode: InputProcessingMode? = null,
  /**
   * A customer added, arbitrary description for any kind of grouping, notification or note purpose
   */
  public val description: String? = null,
) : Input
