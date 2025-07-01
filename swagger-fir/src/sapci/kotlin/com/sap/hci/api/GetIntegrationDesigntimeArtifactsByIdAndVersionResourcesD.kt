package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationDesigntimeArtifacts(Id='{Id}',Version='{Version}')ResourcesD")
public data class GetIntegrationDesigntimeArtifactsByIdAndVersionResourcesD(
  public val results: Resource,
)
