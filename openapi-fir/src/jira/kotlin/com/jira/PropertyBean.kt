package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PropertyBean")
public data class PropertyBean(
  public val id: String? = null,
  public val key: String? = null,
  public val `value`: String? = null,
)
