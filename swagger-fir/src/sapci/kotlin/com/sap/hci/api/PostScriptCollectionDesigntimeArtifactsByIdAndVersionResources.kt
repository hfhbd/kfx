package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PostScriptCollectionDesigntimeArtifacts(Id='{Id}',Version='{Version}')Resources")
public data class PostScriptCollectionDesigntimeArtifactsByIdAndVersionResources(
  public val d: Resource? = null,
)
