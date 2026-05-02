package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class BoardCreateBean(
  public val filterId: Long? = null,
  public val name: String? = null,
  public val type: String? = null,
)
