package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetServiceEndpoints")
public data class GetServiceEndpoints(
  public val `value`: ServiceEndpoint,
)
