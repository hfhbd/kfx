package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IntegrationDesigntimeArtifact-update")
public data class IntegrationDesigntimeArtifactUpdate(
  public val Name: String? = null,
  public val ArtifactContent: String? = null,
)
