package com.jira

import kotlin.Boolean
import kotlinx.serialization.Serializable

@Serializable
public data class RestInvocationHistory(
  public val empty: Boolean? = null,
)
