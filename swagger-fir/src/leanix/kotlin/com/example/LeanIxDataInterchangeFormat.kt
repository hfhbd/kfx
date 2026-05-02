package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * Represents a LeanIX Data Interchange Format (LDIF)
 */
@Serializable
public data class LeanIxDataInterchangeFormat(
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
   * The target API version
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val lxVersion: String,
  /**
   * The optional target workspace
   */
  public val lxWorkspace: String? = null,
  /**
   * A customer added, arbitrary description for any kind of grouping, notification or note purpose
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val description: String? = null,
  /**
   * Optional information on the number of entries in this LDIF chunk. By default the LDIF is processed without the usage of chunks.
   */
  public val chunkInformation: ChunkInformation? = null,
  /**
   * Global variables accessible from all data processors.
   */
  public val customFields: JsonObject? = null,
  /**
   * The list of content changes that are applied within this LDIF
   */
  public val content: List<Content> = emptyList(),
) : Input
