package com.example

import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a LeanIX Data Interchange Format (LDIF)
 */
@Serializable
@SerialName(value = "LeanIxDataInterchangeFormat")
public data class LeanIxDataInterchangeFormat(
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
) : Input
