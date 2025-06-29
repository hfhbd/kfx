package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class TimeTrackingConfigurationBeanTimeFormat {
  @SerialName(value = "pretty")
  Pretty,
  @SerialName(value = "days")
  Days,
  @SerialName(value = "hours")
  Hours,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
