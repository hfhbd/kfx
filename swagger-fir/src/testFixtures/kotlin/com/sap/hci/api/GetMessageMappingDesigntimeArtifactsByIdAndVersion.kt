package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetMessageMappingDesigntimeArtifacts(Id='{Id}',Version='{Version}')")
public data class GetMessageMappingDesigntimeArtifactsByIdAndVersion(
  public val d: MessageMappingDesigntimeArtifact? = null,
)
