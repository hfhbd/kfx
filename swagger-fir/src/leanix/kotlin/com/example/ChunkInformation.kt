package com.example

import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ChunkInformation")
public data class ChunkInformation(
  /**
   * Index of the first data object in this chunk
   */
  public val firstDataObject: Long? = null,
  /**
   * Index of the last data object in this chunk
   */
  public val lastDataObject: Long? = null,
  /**
   * Index of the last data object over all chunks
   */
  public val maxDataObject: Long? = null,
)
