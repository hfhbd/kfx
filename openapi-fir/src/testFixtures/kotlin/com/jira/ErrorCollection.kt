package com.jira

import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ErrorCollection")
public data class ErrorCollection(
  public val errorMessages: List<String>,
  public val errors: Unit? = null,
)
