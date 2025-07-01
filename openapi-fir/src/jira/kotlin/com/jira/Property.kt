package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Property")
public data class Property(
  public val example: String? = null,
  public val key: String? = null,
  public val `value`: String? = null,
)
