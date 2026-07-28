package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationPackagesByIdValueMappingDesigntimeArtifacts")
public data class GetIntegrationPackagesByIdValueMappingDesigntimeArtifacts(
  public val `value`: ValueMappingDesigntimeArtifact? = null,
)
