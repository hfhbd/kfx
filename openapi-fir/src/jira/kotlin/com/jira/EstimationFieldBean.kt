package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class EstimationFieldBean(
  public val displayName: String? = null,
  public val fieldId: String? = null,
)
