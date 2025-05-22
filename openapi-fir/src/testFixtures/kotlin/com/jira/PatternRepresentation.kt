package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PatternRepresentation")
public data class PatternRepresentation(
  public val delay: Int? = null,
  public val enabled: Boolean? = null,
  public val id: Int? = null,
  public val pattern: String? = null,
)
