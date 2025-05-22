package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ValidationError")
public data class ValidationError(
  public val error: String? = null,
  public val `field`: String? = null,
  public val params: List<String>,
)
