package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "BuildAndDeployStatusD")
public data class BuildAndDeployStatusD(
  public val __metadata: BuildAndDeployStatusDMetadata,
  public val TaskId: String,
  public val Status: String,
)
