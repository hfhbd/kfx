package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationPackages('{Id}')MessageMappingDesigntimeArtifacts(Id='{ArtifactId}',Version='{ArtifactVersion}')")
public data class GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion(
  public val `value`: MessageMappingDesigntimeArtifact,
)
