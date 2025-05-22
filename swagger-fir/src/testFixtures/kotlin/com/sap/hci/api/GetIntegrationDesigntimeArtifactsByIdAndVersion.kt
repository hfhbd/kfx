package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationDesigntimeArtifacts(Id='{Id}',Version='{Version}')")
public data class GetIntegrationDesigntimeArtifactsByIdAndVersion(
  public val d: IntegrationDesigntimeArtifact? = null,
)
