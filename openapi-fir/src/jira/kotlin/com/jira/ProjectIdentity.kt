package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectIdentity")
public data class ProjectIdentity(
  public val id: Long? = null,
  public val key: String? = null,
  public val self: String? = null,
)
