package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationPackages")
public data class GetIntegrationPackages(
  public val `value`: IntegrationPackage,
)
