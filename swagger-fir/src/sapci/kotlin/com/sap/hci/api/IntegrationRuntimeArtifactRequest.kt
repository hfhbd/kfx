package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IntegrationRuntimeArtifact_Request")
public data class IntegrationRuntimeArtifactRequest(
  public val Id: String? = null,
  public val Version: String? = null,
  public val Name: String? = null,
  public val Type: String? = null,
)
