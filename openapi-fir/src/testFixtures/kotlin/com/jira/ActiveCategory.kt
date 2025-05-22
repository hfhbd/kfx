package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ActiveCategory")
public data class ActiveCategory(
  public val current: String? = null,
)
