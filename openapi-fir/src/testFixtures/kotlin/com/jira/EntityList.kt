package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EntityList")
public data class EntityList(
  public val entities: List<EntityRefBean>,
)
