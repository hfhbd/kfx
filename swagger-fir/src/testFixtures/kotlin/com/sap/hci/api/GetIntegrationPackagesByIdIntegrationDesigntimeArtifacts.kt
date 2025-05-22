package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationPackages('{Id}')IntegrationDesigntimeArtifacts")
public data class GetIntegrationPackagesByIdIntegrationDesigntimeArtifacts(
  public val `value`: IntegrationDesigntimeArtifact,
)
