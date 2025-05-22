package com.example

import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class InboundProcessorConfigurationInboundProcessorConfiguration(
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
   * The data flow direction, must be [inbound]
   */
  public val processingDirection:
      InboundProcessorConfigurationInboundProcessorConfigurationProcessingDirection,
  /**
   * The processing mode, could be [partial, full]
   */
  public val processingMode:
      InboundProcessorConfigurationInboundProcessorConfigurationProcessingMode? = null,
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
)
