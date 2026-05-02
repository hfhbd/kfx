package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class OrderByPreferences(
  public val orderByOption: String? = null,
  public val projectId: Long? = null,
)
