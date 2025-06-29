package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "MDIDeltaTokenD")
public data class MDIDeltaTokenD(
  public val __metadata: MDIDeltaTokenDMetadata,
  public val Operation: String,
  public val Entity: String,
  public val Version: String,
  public val DeltaToken: String,
  public val LastUpdateTimestamp: String,
)
