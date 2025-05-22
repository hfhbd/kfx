package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "MDIDeltaTokenD__metadata")
public data class MDIDeltaTokenDMetadata(
  public val id: String,
  public val uri: String,
  public val type: String,
)
