package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SubqueryBean")
public data class SubqueryBean(
  public val query: String? = null,
)
