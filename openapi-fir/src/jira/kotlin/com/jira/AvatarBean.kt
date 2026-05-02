package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class AvatarBean(
  public val id: String? = null,
  public val owner: String? = null,
  public val selected: Boolean? = null,
)
