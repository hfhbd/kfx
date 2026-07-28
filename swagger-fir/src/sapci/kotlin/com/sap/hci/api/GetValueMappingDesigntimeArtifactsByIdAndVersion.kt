package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetValueMappingDesigntimeArtifactsByIdAndVersion")
public data class GetValueMappingDesigntimeArtifactsByIdAndVersion(
  public val `value`: ValueMappingDesigntimeArtifact? = null,
)
