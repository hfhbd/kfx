package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IntegrationAdapterDesigntimeArtifact-import")
public data class IntegrationAdapterDesigntimeArtifactImport(
  public val PackageId: String? = null,
  public val ArtifactContent: String? = null,
)
