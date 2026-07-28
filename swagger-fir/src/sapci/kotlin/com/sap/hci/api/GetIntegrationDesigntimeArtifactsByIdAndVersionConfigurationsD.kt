package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsD")
public data class GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsD(
  public val results: Configuration? = null,
)
