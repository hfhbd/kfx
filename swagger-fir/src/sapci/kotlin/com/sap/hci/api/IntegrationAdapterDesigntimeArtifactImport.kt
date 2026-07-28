package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IntegrationAdapterDesigntimeArtifactImport")
public data class IntegrationAdapterDesigntimeArtifactImport(
  public val PackageId: String? = null,
  public val ArtifactContent: String? = null,
)
