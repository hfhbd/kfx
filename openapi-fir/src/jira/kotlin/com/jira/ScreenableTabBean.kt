package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ScreenableTabBean(
  public val id: Long? = null,
  public val name: String? = null,
)
