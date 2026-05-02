package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class Property(
  public val example: String? = null,
  public val key: String? = null,
  public val `value`: String? = null,
)
