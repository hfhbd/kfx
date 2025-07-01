package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationPackages('{Id}')MessageMappingDesigntimeArtifacts")
public data class GetIntegrationPackagesByIdMessageMappingDesigntimeArtifacts(
  public val `value`: MessageMappingDesigntimeArtifact,
)
