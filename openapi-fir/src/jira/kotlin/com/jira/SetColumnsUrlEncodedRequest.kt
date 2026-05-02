package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class SetColumnsUrlEncodedRequest(
  public val username: String? = null,
  public val columns: List<String> = emptyList(),
)
