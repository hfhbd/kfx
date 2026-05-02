package com.example

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class OutboundTagFilter(
  /**
   * The tag groups to retrieve
   */
  public val groups: List<String> = emptyList(),
)
