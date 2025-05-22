package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ColumnLayoutColumnConfig {
  @SerialName(value = "SYSTEM")
  System,
  @SerialName(value = "EXPLICIT")
  Explicit,
  @SerialName(value = "FILTER")
  Filter,
  @SerialName(value = "USER")
  User,
  @SerialName(value = "NONE")
  None,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
