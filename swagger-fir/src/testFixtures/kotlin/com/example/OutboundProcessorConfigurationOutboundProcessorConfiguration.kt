package com.example

import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class OutboundProcessorConfigurationOutboundProcessorConfiguration(
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
   * The data flow direction, must be [outbound]
   */
  public val processingDirection:
      OutboundProcessorConfigurationOutboundProcessorConfigurationProcessingDirection,
  /**
   * The processing mode, could be [partial]
   */
  public val processingMode:
      OutboundProcessorConfigurationOutboundProcessorConfigurationProcessingMode? = null,
  /**
   * A list of facet filters to limit which Fact Sheets are considered for output
   */
  public val scope: Unit,
  /**
   * The list of processors used to evaluate the LDIF data
   */
  public val processors: List<OutboundProcessor>,
  /**
   * Define the target location to which the created LDIF should be uploaded to
   */
  public val dataConsumer: DataConsumer? = null,
  /**
   * The default outbound input for testing with this processor configuration.
   */
  public val defaultInput: Unit? = null,
)
