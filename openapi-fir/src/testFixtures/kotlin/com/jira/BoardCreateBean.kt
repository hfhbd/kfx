package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "BoardCreateBean")
public data class BoardCreateBean(
  public val filterId: Long? = null,
  public val name: String? = null,
  public val type: String? = null,
)
