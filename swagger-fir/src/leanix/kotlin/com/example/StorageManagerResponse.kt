package com.example

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class StorageManagerResponse(
  /**
   * The date until the SAS url for the new blob file is valid.
   */
  public val expireAt: String,
  /**
   * The Shared Accesss Signature (SAS)
   */
  public val SAS: String,
)
