package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ValMaps")
public data class ValMaps(
  public val Id: String? = null,
  public val Value: ValMapsValue? = null,
)
