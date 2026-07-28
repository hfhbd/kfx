package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion")
public data class GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion(
  public val `value`: MessageMappingDesigntimeArtifact? = null,
)
