package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ComponentBeanAssigneeType {
  @SerialName(value = "PROJECT_DEFAULT")
  ProjectDefault,
  @SerialName(value = "COMPONENT_LEAD")
  ComponentLead,
  @SerialName(value = "PROJECT_LEAD")
  ProjectLead,
  @SerialName(value = "UNASSIGNED")
  Unassigned,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
