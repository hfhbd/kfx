package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EstimationConfigBean")
public data class EstimationConfigBean(
  public val `field`: EstimationFieldBean? = null,
  public val type: String? = null,
)
