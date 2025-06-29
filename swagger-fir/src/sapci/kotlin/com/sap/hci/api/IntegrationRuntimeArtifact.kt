package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IntegrationRuntimeArtifact")
public data class IntegrationRuntimeArtifact(
  public val Id: String? = null,
  public val Version: String? = null,
  public val Name: String? = null,
  public val Type: String? = null,
  public val DeployedBy: String? = null,
  public val DeployedOn: String? = null,
  public val Status: String? = null,
  public val ErrorInformation: RuntimeArtifactErrorInformation? = null,
)
