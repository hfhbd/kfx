package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "MessageMappingDesigntimeArtifact-update")
public data class MessageMappingDesigntimeArtifactUpdate(
  public val Name: String? = null,
  public val Description: String? = null,
  public val ArtifactContent: String? = null,
)
