package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class EntityPropertyKeyBean(
  public val key: String? = null,
  public val self: String? = null,
)
