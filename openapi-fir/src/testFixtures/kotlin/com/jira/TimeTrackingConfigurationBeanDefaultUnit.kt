package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class TimeTrackingConfigurationBeanDefaultUnit {
  @SerialName(value = "minute")
  Minute,
  @SerialName(value = "hour")
  Hour,
  @SerialName(value = "day")
  Day,
  @SerialName(value = "week")
  Week,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
