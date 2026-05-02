package com.jira

import kotlinx.serialization.Serializable

@Serializable
public data class AvailableProjectsPaginatedBean(
  public val results: PageBeanProjectBean? = null,
)
