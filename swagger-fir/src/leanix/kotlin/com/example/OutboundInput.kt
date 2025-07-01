package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OutboundInput")
public data class OutboundInput(
  /**
   * The type of connector that is used
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public override val connectorType: String,
  /**
   * The identifier of the connector instance
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public override val connectorId: String,
  /**
   * The version of the connector that is expected to process this LDIF file
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public override val connectorVersion: String,
  /**
   * Optional additional options to parse this LDIF request
   */
  public override val processingMode: InputProcessingMode? = null,
  /**
   * A customer added, arbitrary description for any kind of grouping, notification or note purpose
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val description: String? = null,
) : Input
