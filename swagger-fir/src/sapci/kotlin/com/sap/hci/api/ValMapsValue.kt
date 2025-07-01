package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ValMapsValue")
public data class ValMapsValue(
  public val results: Value,
)
