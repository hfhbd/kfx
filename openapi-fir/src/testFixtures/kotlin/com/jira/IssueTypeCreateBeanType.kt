package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class IssueTypeCreateBeanType {
  @SerialName(value = "subtask")
  Subtask,
  @SerialName(value = "standard")
  Standard,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
