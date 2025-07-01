package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationPackages('{Id}')ValueMappingDesigntimeArtifacts")
public data class GetIntegrationPackagesByIdValueMappingDesigntimeArtifacts(
  public val `value`: ValueMappingDesigntimeArtifact,
)
