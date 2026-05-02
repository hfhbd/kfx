package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ProjectIdentity(
  public val id: Long? = null,
  public val key: String? = null,
  public val self: String? = null,
)
