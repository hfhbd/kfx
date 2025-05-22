package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "BuildAndDeployStatusD__metadata")
public data class BuildAndDeployStatusDMetadata(
  public val id: String,
  public val uri: String,
  public val type: String,
)
