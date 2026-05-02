package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class EntityPropertyBean(
  public val key: String? = null,
  public val `value`: String? = null,
)
