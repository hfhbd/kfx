package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurations")
public data class GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurations(
  public val d: GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsD? = null,
)
