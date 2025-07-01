package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Statistics")
public data class Statistics(
  public val empty: Boolean? = null,
)
