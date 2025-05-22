package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetValueMappingDesigntimeArtifacts")
public data class GetValueMappingDesigntimeArtifacts(
  public val `value`: ValueMappingDesigntimeArtifact,
)
