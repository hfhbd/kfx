package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PostScriptCollectionDesigntimeArtifacts")
public data class PostScriptCollectionDesigntimeArtifacts(
  public val d: ScriptCollectionDesigntimeArtifact? = null,
)
