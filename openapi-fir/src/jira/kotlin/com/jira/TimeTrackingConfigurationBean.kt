package com.jira

import kotlin.Double
import kotlinx.serialization.Serializable

@Serializable
public data class TimeTrackingConfigurationBean(
  public val defaultUnit: TimeTrackingConfigurationBeanDefaultUnit? = null,
  public val timeFormat: TimeTrackingConfigurationBeanTimeFormat? = null,
  public val workingDaysPerWeek: Double? = null,
  public val workingHoursPerDay: Double? = null,
)
