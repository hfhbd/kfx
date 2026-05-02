package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ApplicationPropertyBean(
  public val id: String? = null,
  public val `value`: String? = null,
)
