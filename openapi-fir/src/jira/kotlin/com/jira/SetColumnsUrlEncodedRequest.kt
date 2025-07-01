package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SetColumnsUrlEncodedRequest")
public data class SetColumnsUrlEncodedRequest(
  public val username: String? = null,
  public val columns: List<String>,
)
