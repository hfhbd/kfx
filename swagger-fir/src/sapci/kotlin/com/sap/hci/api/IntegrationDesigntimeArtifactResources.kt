package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IntegrationDesigntimeArtifactResources")
public data class IntegrationDesigntimeArtifactResources(
  public val results: Resource,
)
