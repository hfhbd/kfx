package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RestInvocationHistory")
public data class RestInvocationHistory(
  public val empty: Boolean? = null,
)
