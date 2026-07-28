package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetIntegrationPackagesByIdScriptCollectionDesigntimeArtifacts")
public data class GetIntegrationPackagesByIdScriptCollectionDesigntimeArtifacts(
  public val `value`: ScriptCollectionDesigntimeArtifact? = null,
)
