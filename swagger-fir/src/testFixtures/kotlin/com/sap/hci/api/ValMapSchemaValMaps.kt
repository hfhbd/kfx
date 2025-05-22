package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ValMapSchemaValMaps")
public data class ValMapSchemaValMaps(
  public val results: ValMaps,
)
