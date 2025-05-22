package com.jira

import kotlin.Double
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "TimeTrackingConfigurationBean")
public data class TimeTrackingConfigurationBean(
  public val defaultUnit: TimeTrackingConfigurationBeanDefaultUnit? = null,
  public val timeFormat: TimeTrackingConfigurationBeanTimeFormat? = null,
  public val workingDaysPerWeek: Double? = null,
  public val workingHoursPerDay: Double? = null,
)
