package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "StatusCategoryJsonBean")
public data class StatusCategoryJsonBean(
  public val colorName: String? = null,
  public val id: Long? = null,
  public val key: String? = null,
  public val name: String? = null,
  public val self: String? = null,
)
