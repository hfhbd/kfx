package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IntegrationDesigntimeArtifactConfigurations")
public data class IntegrationDesigntimeArtifactConfigurations(
  public val results: Configuration,
)
