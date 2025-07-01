package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationRuntimeArtifactsD")
public data class GetIntegrationRuntimeArtifactsD(
  public val results: IntegrationRuntimeArtifact,
)
