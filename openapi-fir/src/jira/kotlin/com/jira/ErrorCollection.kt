package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ErrorCollection")
public data class ErrorCollection(
  public val errorMessages: List<String>,
  public val errors: Map<String, String>? = null,
)
