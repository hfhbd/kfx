package com.example

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "OutboundTagFilter")
public data class OutboundTagFilter(
  /**
   * The tag groups to retrieve
   */
  public val groups: List<String>,
)
