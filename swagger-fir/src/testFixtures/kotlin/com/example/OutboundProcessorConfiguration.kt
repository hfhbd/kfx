package com.example

import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OutboundProcessorConfiguration")
public data class OutboundProcessorConfiguration(
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
   * The processing mode, could be [partial]
   */
  public override val processingMode: ProcessorConfigurationProcessingMode? = null,
  /**
   * When set to true, all synchronizations runs processed by this processor configuration will be executed sequentially instead of in parallel.
   */
  public override val sequentialExecution: Boolean? = null,
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
) : ProcessorConfiguration
