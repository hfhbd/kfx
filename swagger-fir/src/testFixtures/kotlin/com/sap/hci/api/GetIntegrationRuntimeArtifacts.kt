package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationRuntimeArtifacts")
public data class GetIntegrationRuntimeArtifacts(
  public val d: GetIntegrationRuntimeArtifactsD? = null,
)
