package com.jira

import kotlin.Long
import kotlinx.serialization.Serializable

@Serializable
public data class IdBean(
  public val id: Long? = null,
)
