package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetScriptCollectionDesigntimeArtifacts(Id='{Id}',Version='{Version}')")
public data class GetScriptCollectionDesigntimeArtifactsByIdAndVersion(
  public val d: ScriptCollectionDesigntimeArtifact? = null,
)
