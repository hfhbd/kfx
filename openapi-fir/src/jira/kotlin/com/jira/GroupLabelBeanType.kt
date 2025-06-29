package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GroupLabelBeanType {
  @SerialName(value = "ADMIN")
  Admin,
  @SerialName(value = "SINGLE")
  Single,
  @SerialName(value = "MULTIPLE")
  Multiple,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
