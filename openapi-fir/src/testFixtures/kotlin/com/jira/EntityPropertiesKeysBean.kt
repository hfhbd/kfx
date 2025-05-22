package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EntityPropertiesKeysBean")
public data class EntityPropertiesKeysBean(
  public val keys: List<EntityPropertyKeyBean>,
)
