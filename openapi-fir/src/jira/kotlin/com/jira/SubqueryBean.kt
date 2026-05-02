package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class SubqueryBean(
  public val query: String? = null,
)
