package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetScriptCollectionDesigntimeArtifactsByIdAndVersion")
public data class GetScriptCollectionDesigntimeArtifactsByIdAndVersion(
  public val d: ScriptCollectionDesigntimeArtifact? = null,
)
