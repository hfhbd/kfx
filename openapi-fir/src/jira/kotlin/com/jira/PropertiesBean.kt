package com.jira

import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable

@Serializable
public data class PropertiesBean(
  public val properties: Map<String, String> = emptyMap(),
)
