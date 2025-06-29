package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator(discriminator = "processingDirection")
@SerialName(value = "Input")
public sealed interface Input {
  /**
   * The type of connector that is used
   */
  public val connectorType: String

  /**
   * The identifier of the connector instance
   */
  public val connectorId: String

  /**
   * The version of the connector that is expected to process this LDIF file
   */
  public val connectorVersion: String

  /**
   * The processing mode, could be [partial, full]
   */
  public val processingMode: InputProcessingMode?
}
