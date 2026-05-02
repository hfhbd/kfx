package com.jira

import kotlin.Boolean
import kotlinx.serialization.Serializable

@Serializable
public data class RegisterItemHolder(
  public val isLocked: Boolean? = null,
  public val isManaged: Boolean? = null,
)
