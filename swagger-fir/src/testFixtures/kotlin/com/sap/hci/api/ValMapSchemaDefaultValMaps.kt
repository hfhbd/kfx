package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ValMapSchemaDefaultValMaps")
public data class ValMapSchemaDefaultValMaps(
  public val results: ValMaps,
)
