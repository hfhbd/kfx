package com.jira

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PropertiesBean")
public data class PropertiesBean(
  public val properties: Unit? = null,
)
