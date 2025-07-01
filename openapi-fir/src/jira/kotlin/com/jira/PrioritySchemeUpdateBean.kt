package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PrioritySchemeUpdateBean")
public data class PrioritySchemeUpdateBean(
  public val defaultOptionId: String? = null,
  public val description: String? = null,
  public val id: Long? = null,
  public val name: String? = null,
  public val optionIds: List<String>,
)
