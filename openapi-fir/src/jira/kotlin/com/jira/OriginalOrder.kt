package com.jira

import kotlin.Long
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class OriginalOrder(
  public val entries: List<Long> = emptyList(),
)
