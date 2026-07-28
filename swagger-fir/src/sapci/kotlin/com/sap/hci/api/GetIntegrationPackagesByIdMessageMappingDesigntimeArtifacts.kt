package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationPackagesByIdMessageMappingDesigntimeArtifacts")
public data class GetIntegrationPackagesByIdMessageMappingDesigntimeArtifacts(
  public val `value`: MessageMappingDesigntimeArtifact? = null,
)
