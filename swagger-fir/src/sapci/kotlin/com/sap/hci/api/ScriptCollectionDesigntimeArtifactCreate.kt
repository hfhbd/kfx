package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ScriptCollectionDesigntimeArtifact-create")
public data class ScriptCollectionDesigntimeArtifactCreate(
  public val Name: String? = null,
  public val Id: String? = null,
  public val PackageId: String? = null,
  public val ArtifactContent: String? = null,
)
