package com.jira

import kotlin.Boolean
import kotlinx.serialization.Serializable

@Serializable
public data class Statistics(
  public val empty: Boolean? = null,
)
