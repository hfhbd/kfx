package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EstimationFieldBean")
public data class EstimationFieldBean(
  public val displayName: String? = null,
  public val fieldId: String? = null,
)
