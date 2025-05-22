package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class DefaultShareScopeBeanScope {
  @SerialName(value = "GLOBAL")
  Global,
  @SerialName(value = "AUTHENTICATED")
  Authenticated,
  @SerialName(value = "PRIVATE")
  Private,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
