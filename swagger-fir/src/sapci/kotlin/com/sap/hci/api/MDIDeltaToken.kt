package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "MDIDeltaToken")
public data class MDIDeltaToken(
  public val d: MDIDeltaTokenD,
)
