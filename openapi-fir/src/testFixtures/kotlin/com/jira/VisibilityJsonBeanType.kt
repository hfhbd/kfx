package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class VisibilityJsonBeanType {
  @SerialName(value = "group")
  Group,
  @SerialName(value = "role")
  Role,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
