package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class PermissionJsonBeanType {
  @SerialName(value = "GLOBAL")
  Global,
  @SerialName(value = "PROJECT")
  Project,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
