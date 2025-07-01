package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RegisterItemHolder")
public data class RegisterItemHolder(
  public val isLocked: Boolean? = null,
  public val isManaged: Boolean? = null,
)
