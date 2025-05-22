package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationDesigntimeArtifacts(Id='{Id}',Version='{Version}')ConfigurationsD")
public data class GetIntegrationDesigntimeArtifactsByIdAndVersionConfigurationsD(
  public val results: Configuration,
)
