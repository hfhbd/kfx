package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OrderByPreferences")
public data class OrderByPreferences(
  public val orderByOption: String? = null,
  public val projectId: Long? = null,
)
