package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class Application(
  public val name: String? = null,
  public val type: String? = null,
)
