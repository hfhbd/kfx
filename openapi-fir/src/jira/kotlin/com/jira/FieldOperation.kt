package com.jira

import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "FieldOperation")
public data class FieldOperation(
  public val operation: String? = null,
  public val `value`: Unit? = null,
)
