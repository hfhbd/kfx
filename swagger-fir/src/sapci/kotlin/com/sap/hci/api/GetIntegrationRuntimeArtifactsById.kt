package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationRuntimeArtifacts('{Id}')")
public data class GetIntegrationRuntimeArtifactsById(
  public val d: IntegrationRuntimeArtifact? = null,
)
