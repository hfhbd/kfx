package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetBuildAndDeployStatus(TaskId='{taskId}')")
public data class GetBuildAndDeployStatusByTaskId(
  public val d: BuildAndDeployStatus? = null,
)
