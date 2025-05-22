package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomFieldOptionBean")
public data class CustomFieldOptionBean(
  public val childrenIds: List<Long>,
  public val disabled: Boolean? = null,
  public val id: Long? = null,
  public val self: String? = null,
  public val `value`: String? = null,
)
