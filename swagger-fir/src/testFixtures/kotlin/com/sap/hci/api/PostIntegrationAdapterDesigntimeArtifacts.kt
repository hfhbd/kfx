package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PostIntegrationAdapterDesigntimeArtifacts")
public data class PostIntegrationAdapterDesigntimeArtifacts(
  public val d: IntegrationAdapterDesigntimeArtifact? = null,
)
