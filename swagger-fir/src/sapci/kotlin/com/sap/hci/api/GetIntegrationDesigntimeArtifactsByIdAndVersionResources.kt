package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationDesigntimeArtifacts(Id='{Id}',Version='{Version}')Resources")
public data class GetIntegrationDesigntimeArtifactsByIdAndVersionResources(
  public val d: GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesD? = null,
)
