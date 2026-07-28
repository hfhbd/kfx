package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ScriptCollectionDesigntimeArtifactUpdate")
public data class ScriptCollectionDesigntimeArtifactUpdate(
  public val Name: String? = null,
  public val ArtifactContent: String? = null,
)
