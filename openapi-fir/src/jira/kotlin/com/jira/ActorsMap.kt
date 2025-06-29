package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ActorsMap")
public data class ActorsMap(
  public val empty: Boolean? = null,
)
