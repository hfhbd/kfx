package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetBuildAndDeployStatusByTaskId")
public data class GetBuildAndDeployStatusByTaskId(
  public val d: BuildAndDeployStatus? = null,
)
