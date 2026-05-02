package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class OptionString(
  public val defined: Boolean? = null,
  public val empty: Boolean? = null,
  public val orNull: String? = null,
)
