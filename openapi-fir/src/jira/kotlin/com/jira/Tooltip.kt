package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Tooltip")
public data class Tooltip(
  public val id: String? = null,
)
