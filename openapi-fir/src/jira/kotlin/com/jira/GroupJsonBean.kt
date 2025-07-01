package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GroupJsonBean")
public data class GroupJsonBean(
  public val name: String? = null,
  public val self: String? = null,
)
