package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EntityPropertyKeyBean")
public data class EntityPropertyKeyBean(
  public val key: String? = null,
  public val self: String? = null,
)
