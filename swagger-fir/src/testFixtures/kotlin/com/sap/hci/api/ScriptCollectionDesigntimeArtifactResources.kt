package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ScriptCollectionDesigntimeArtifactResources")
public data class ScriptCollectionDesigntimeArtifactResources(
  public val results: Resource,
)
