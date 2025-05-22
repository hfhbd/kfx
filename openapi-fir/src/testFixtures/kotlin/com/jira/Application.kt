package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Application")
public data class Application(
  public val name: String? = null,
  public val type: String? = null,
)
