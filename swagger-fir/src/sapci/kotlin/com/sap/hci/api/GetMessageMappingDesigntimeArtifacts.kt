package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetMessageMappingDesigntimeArtifacts")
public data class GetMessageMappingDesigntimeArtifacts(
  public val `value`: MessageMappingDesigntimeArtifact,
)
