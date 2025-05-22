package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "MessageMappingDesigntimeArtifact-create")
public data class MessageMappingDesigntimeArtifactCreate(
  public val Name: String? = null,
  public val Id: String? = null,
  public val PackageId: String? = null,
  public val Description: String? = null,
  public val ArtifactContent: String? = null,
)
