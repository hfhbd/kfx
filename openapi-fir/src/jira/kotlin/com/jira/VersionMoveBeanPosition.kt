package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class VersionMoveBeanPosition {
  @SerialName(value = "Earlier")
  Earlier,
  @SerialName(value = "Later")
  Later,
  @SerialName(value = "First")
  First,
  @SerialName(value = "Last")
  Last,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
