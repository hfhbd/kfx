package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IncludedFields")
public data class IncludedFields(
  public val included: List<String>,
)
