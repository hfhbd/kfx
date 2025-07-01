package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Status")
public data class Status(
  public val icon: Icon? = null,
  public val resolved: Boolean? = null,
)
