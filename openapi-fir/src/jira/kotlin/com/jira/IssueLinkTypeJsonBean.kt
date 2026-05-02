package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class IssueLinkTypeJsonBean(
  public val id: String? = null,
  public val inward: String? = null,
  public val name: String? = null,
  public val outward: String? = null,
  public val self: String? = null,
)
