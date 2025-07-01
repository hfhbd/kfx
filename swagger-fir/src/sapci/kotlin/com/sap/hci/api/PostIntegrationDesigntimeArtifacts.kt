package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PostIntegrationDesigntimeArtifacts")
public data class PostIntegrationDesigntimeArtifacts(
  public val d: IntegrationDesigntimeArtifact? = null,
)
