package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "StorageManagerResponse")
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
