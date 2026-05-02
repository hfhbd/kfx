package com.jira

import kotlin.Boolean
import kotlinx.serialization.Serializable

@Serializable
public data class Status(
  public val icon: Icon? = null,
  public val resolved: Boolean? = null,
)
