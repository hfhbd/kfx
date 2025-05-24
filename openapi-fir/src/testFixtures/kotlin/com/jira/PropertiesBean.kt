package com.jira

import kotlin.String
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PropertiesBean")
public data class PropertiesBean(
  public val properties: Map<String, String>? = null,
)
