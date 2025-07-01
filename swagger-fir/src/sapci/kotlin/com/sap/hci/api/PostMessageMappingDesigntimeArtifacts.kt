package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PostMessageMappingDesigntimeArtifacts")
public data class PostMessageMappingDesigntimeArtifacts(
  public val d: MessageMappingDesigntimeArtifactCreate? = null,
)
