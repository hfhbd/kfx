package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RuntimeArtifactErrorInformation")
public data class RuntimeArtifactErrorInformation(
  public val Id: String? = null,
)
