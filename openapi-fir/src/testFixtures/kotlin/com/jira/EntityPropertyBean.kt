package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EntityPropertyBean")
public data class EntityPropertyBean(
  public val key: String? = null,
  public val `value`: String? = null,
)
