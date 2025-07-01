package com.example

import kotlin.Int
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ChunkInformation")
public data class ChunkInformation(
  /**
   * Index of the first data object in this chunk
   */
  public val firstDataObject: Int? = null,
  /**
   * Index of the last data object in this chunk
   */
  public val lastDataObject: Int? = null,
  /**
   * Index of the last data object over all chunks
   */
  public val maxDataObject: Int? = null,
)
