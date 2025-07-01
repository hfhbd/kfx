package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EntityRefBean")
public data class EntityRefBean(
  public val key: String? = null,
  public val name: String? = null,
  public val type: EntityTypeBean? = null,
)
