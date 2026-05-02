package com.jira

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AvailableProjectsPaginatedBean")
public data class AvailableProjectsPaginatedBean(
  public val results: PageBeanProjectBean? = null,
)
