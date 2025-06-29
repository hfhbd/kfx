package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "BoardBean")
public data class BoardBean(
  public val id: Long? = null,
  public val name: String? = null,
  public val self: String? = null,
  public val type: String? = null,
)
