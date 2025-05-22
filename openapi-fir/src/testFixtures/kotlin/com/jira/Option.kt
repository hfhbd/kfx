package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Option")
public data class Option(
  public val childOptions: List<Option>,
  public val id: Long? = null,
  public val name: String? = null,
  public val optionId: Long? = null,
  public val `value`: String? = null,
)
