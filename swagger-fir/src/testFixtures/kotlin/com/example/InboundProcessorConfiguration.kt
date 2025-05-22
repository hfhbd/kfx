package com.example

import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "InboundProcessorConfiguration")
public data class InboundProcessorConfiguration(
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
   * The processing mode, could be [partial, full]
   */
  public override val processingMode: ProcessorConfigurationProcessingMode? = null,
  /**
   * When set to true, all synchronizations runs processed by this processor configuration will be executed sequentially instead of in parallel.
   */
  public override val sequentialExecution: Boolean? = null,
  /**
   * The list of processors used to evaluate the LDIF data
   */
  public val processors: List<InboundProcessor>,
  /**
   * The deletion scope definition used to delete untouched entities on a 'full' sync mode
   */
  public val deletionScope: Unit? = null,
  /**
   * The global variable definition used to define default values
   */
  public val variables: Unit? = null,
  /**
   * Definition of the provider which provides the inbound LDIF.
   */
  public val dataProvider: Unit? = null,
  /**
   * Credentials setting for the synchronization run.
   */
  public val credentials: Unit? = null,
  /**
   * Configuration for the LDIF to be created, in case 'WriteToLdif' is used
   */
  public val targetLdif: TargetLdifConfiguration? = null,
  /**
   * The default LDIF for testing with this processor configuration.
   */
  public val defaultInput: Unit? = null,
) : ProcessorConfiguration
