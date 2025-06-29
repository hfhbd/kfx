package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PostValueMappingDesigntimeArtifacts")
public data class PostValueMappingDesigntimeArtifacts(
  public val d: ValueMappingDesigntimeArtifact? = null,
)
