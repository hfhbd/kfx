package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class EntityRefBean(
  public val key: String? = null,
  public val name: String? = null,
  public val type: EntityTypeBean? = null,
)
