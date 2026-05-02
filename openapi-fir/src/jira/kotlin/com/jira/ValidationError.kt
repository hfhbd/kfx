package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class ValidationError(
  public val error: String? = null,
  public val `field`: String? = null,
  public val params: List<String> = emptyList(),
)
