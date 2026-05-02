package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ActiveCategory(
  public val current: String? = null,
)
