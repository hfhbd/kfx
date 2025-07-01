package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DashboardBean")
public data class DashboardBean(
  public val id: String? = null,
  public val name: String? = null,
  public val self: String? = null,
  public val view: String? = null,
)
