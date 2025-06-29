package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "MessageMappingDesigntimeArtifactResources")
public data class MessageMappingDesigntimeArtifactResources(
  public val results: Resource,
)
