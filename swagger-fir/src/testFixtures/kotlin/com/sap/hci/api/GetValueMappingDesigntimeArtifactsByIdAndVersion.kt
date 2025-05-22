package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetValueMappingDesigntimeArtifacts(Id='{Id}',Version='{Version}')")
public data class GetValueMappingDesigntimeArtifactsByIdAndVersion(
  public val `value`: ValueMappingDesigntimeArtifact,
)
