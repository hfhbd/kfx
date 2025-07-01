package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PostIntegrationDesigntimeArtifacts(Id='{Id}',Version='{Version}')Resources")
public data class PostIntegrationDesigntimeArtifactsByIdAndVersionResources(
  public val d: Resource? = null,
)
