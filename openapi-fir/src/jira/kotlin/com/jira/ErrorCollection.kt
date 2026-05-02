package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ErrorCollection")
public data class ErrorCollection(
  public val errorMessages: List<String> = emptyList(),
  public val errors: Map<String, String> = emptyMap(),
)
