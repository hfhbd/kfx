package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PrioritySchemeBean")
public data class PrioritySchemeBean(
  public val defaultOptionId: String? = null,
  public val defaultScheme: Boolean? = null,
  public val description: String? = null,
  public val id: Long? = null,
  public val name: String? = null,
  public val optionIds: List<String>,
  public val projectKeys: List<String>,
  public val self: String? = null,
)
