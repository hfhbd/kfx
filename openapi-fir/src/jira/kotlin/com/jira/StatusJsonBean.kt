package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "StatusJsonBean")
public data class StatusJsonBean(
  public val description: String? = null,
  public val iconUrl: String? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val self: String? = null,
  public val statusCategory: StatusCategoryJsonBean? = null,
  public val statusColor: String? = null,
)
