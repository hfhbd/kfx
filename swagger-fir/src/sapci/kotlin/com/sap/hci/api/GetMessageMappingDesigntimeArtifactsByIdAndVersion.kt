package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetMessageMappingDesigntimeArtifactsByIdAndVersion")
public data class GetMessageMappingDesigntimeArtifactsByIdAndVersion(
  public val d: MessageMappingDesigntimeArtifact? = null,
)
