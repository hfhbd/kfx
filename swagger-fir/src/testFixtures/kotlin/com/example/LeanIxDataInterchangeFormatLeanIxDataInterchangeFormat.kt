package com.example

import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class LeanIxDataInterchangeFormatLeanIxDataInterchangeFormat(
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
   * The target API version
   */
  public val lxVersion: String,
  /**
   * The optional target workspace
   */
  public val lxWorkspace: String? = null,
  /**
   * A customer added, arbitrary description for any kind of grouping, notification or note purpose
   */
  public val description: String? = null,
  /**
   * The direction of the data flow
   */
  public val processingDirection:
      LeanIxDataInterchangeFormatLeanIxDataInterchangeFormatProcessingDirection,
  /**
   * Optional additional options to parse this LDIF request
   */
  public val processingMode:
      LeanIxDataInterchangeFormatLeanIxDataInterchangeFormatProcessingMode? = null,
  /**
   * Optional information on the number of entries in this LDIF chunk. By default the LDIF is processed without the usage of chunks.
   */
  public val chunkInformation: ChunkInformation? = null,
  /**
   * Global variables accessible from all data processors.
   */
  public val customFields: Unit? = null,
  /**
   * The list of content changes that are applied within this LDIF
   */
  public val content: List<Content>,
)
