package com.jira

import kotlin.Long
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OriginalOrder")
public data class OriginalOrder(
  public val entries: List<Long>,
)
